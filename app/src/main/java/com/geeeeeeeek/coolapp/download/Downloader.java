package com.geeeeeeeek.coolapp.download;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;

import com.geeeeeeeek.coolapp.http.RetrofitFactory;
import com.geeeeeeeek.coolapp.utils.CommonUtils;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/17
 * Time: 11:00 AM
 */
public class Downloader {
    public static boolean enableLog = true;
    public static void download(final String url, final String filePath, final DownloadCallback callback) {
        if (TextUtils.isEmpty(filePath)) {
            if (null != callback) {
                callback.onError("url or path empty");
            }
            return;
        }
        File oldFile = new File(filePath);
        if (oldFile.exists()) {
            if (null != callback) {
                callback.onFinish(oldFile);
            }
            return;
        }
        DownloadListener listener = new DownloadListener() {
            @Override
            public void onStart(ResponseBody responseBody) {
                saveFile(responseBody, url, filePath, callback);
            }
        };
        RetrofitFactory.downloadFile(url, CommonUtils.getTempFile(url, filePath).length(), listener, new Observer<ResponseBody>() {
            @Override
            public void onSubscribe(Disposable d) {
                if (null != callback) {
                    callback.onStart(d);
                }
            }
            @Override
            public void onNext(final ResponseBody responseBody) {
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                if (null != callback) {
                    callback.onError(e.getMessage());
                }
            }
            @Override
            public void onComplete() {
            }
        });
    }
    private static void saveFile(final ResponseBody responseBody, String url, final String filePath, final DownloadCallback callback) {
        boolean downloadSuccss = true;
        final File tempFile = CommonUtils.getTempFile(url, filePath);
        try {
            writeFileToDisk(responseBody, tempFile.getAbsolutePath(), callback);
        } catch (Exception e) {
            e.printStackTrace();
            downloadSuccss = false;
        }
        if (downloadSuccss) {
            final boolean renameSuccess = tempFile.renameTo(new File(filePath));
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    if (null != callback && renameSuccess) {
                        callback.onFinish(new File(filePath));
                    }
                }
            });
        }
    }

    private static void writeFileToDisk(ResponseBody responseBody, String filePath, final DownloadCallback callback) throws IOException {
        long totalByte = responseBody.contentLength();
        long downloadByte = 0;
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        byte[] buffer = new byte[1024 * 4];
        RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rwd");
        long tempFileLen = file.length();
        randomAccessFile.seek(tempFileLen);
        while (true) {
            int len = responseBody.byteStream().read(buffer);
            if (len == -1) {
                break;
            }
            randomAccessFile.write(buffer, 0, len);
            downloadByte += len;
            callbackProgress(tempFileLen + totalByte, tempFileLen + downloadByte, callback);
        }
        randomAccessFile.close();
    }
    private static void callbackProgress(final long totalByte, final long downloadByte, final DownloadCallback callback) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @SuppressLint("DefaultLocale")
            @Override
            public void run() {
                if (null != callback) {
                    callback.onProgress(totalByte, downloadByte, (int) ((downloadByte * 100) / totalByte));
                }
            }
        });
    }
}