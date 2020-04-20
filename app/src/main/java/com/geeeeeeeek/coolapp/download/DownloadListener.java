package com.geeeeeeeek.coolapp.download;

import okhttp3.ResponseBody;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/17
 * Time: 11:03 AM
 */
public interface DownloadListener {
    void onStart(ResponseBody responseBody);
}