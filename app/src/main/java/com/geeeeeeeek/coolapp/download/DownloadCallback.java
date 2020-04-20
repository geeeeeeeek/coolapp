package com.geeeeeeeek.coolapp.download;

import java.io.File;

import io.reactivex.disposables.Disposable;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/17
 * Time: 11:02 AM
 */
public interface DownloadCallback {
    void onStart(Disposable d);
    void onProgress(long totalByte, long currentByte, int progress);
    void onFinish(File file);
    void onError(String msg);
}
