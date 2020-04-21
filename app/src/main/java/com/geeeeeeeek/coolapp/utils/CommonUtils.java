package com.geeeeeeeek.coolapp.utils;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import okhttp3.ResponseBody;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/17
 * Time: 11:11 AM
 */
public class CommonUtils {
    public static final String TAG = "CommonUtils";

    public static String getDateToString(long milSecond, String pattern) {
        Date date = new Date(milSecond);
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(date);
    }

    public static boolean writeResponseBodyToDisk(ResponseBody body, File file, DownloadProgressListener progressListener) {
        try {
            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];
                long totalSize = body.contentLength();
                if (totalSize <= 0){
                    Log.w(TAG, "no content length!");
                    return false;
                }
                long fileSizeDownloaded = 0;
                inputStream = body.byteStream();
                outputStream = new FileOutputStream(file);
                while (true) {
                    int read = inputStream.read(fileReader);
                    if (read == -1) {
                        break;
                    }
                    Log.d(TAG, "inputStream size: " + totalSize);

                    outputStream.write(fileReader, 0, read);
                    fileSizeDownloaded += read;
                    progressListener.onProgress(fileSizeDownloaded, totalSize);
                    Log.d(TAG, "file download: " + fileSizeDownloaded + " of " + totalSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }

    public static void unzip(File zipFile, File outFile) {
        ZipInputStream zis = null;
        try {
            zis = new ZipInputStream(new BufferedInputStream(new FileInputStream(zipFile)));
            int count;
            byte[] buffer = new byte[8192];
            if (zis.getNextEntry() != null) {
                if (!outFile.exists()) {
                    outFile.createNewFile();
                }
                FileOutputStream fout = new FileOutputStream(outFile);
                try {
                    while ((count = zis.read(buffer)) != -1)
                        fout.write(buffer, 0, count);
                } finally {
                    fout.close();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                zis.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static File getTempFile(String url, String filePath) {
        File parentFile = new File(filePath).getParentFile();
        String md5 = bytes2HexString(url.getBytes());
        return new File(parentFile.getAbsolutePath(), md5 + ".temp");
    }

    private static final char HEX_DIGITS[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static String bytes2HexString(final byte[] bytes) {
        if (bytes == null) {
            return "";
        }
        int len = bytes.length;
        if (len <= 0) {
            return "";
        }
        char[] ret = new char[len << 1];
        for (int i = 0, j = 0; i < len; i++) {
            ret[j++] = HEX_DIGITS[bytes[i] >> 4 & 0x0f];
            ret[j++] = HEX_DIGITS[bytes[i] & 0x0f];
        }
        return new String(ret);
    }
}