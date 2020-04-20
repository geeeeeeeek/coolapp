package com.geeeeeeeek.coolapp.http;

import com.geeeeeeeek.coolapp.download.DownloadListener;

import java.io.IOException;

import androidx.annotation.NonNull;
import okhttp3.Interceptor;
import okhttp3.Response;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/17
 * Time: 11:04 AM
 */
public class DownloadInterceptor implements Interceptor {
    private DownloadListener listener;
    public DownloadInterceptor(DownloadListener listener) {
        this.listener = listener;
    }
    @Override
    public Response intercept(@NonNull Interceptor.Chain chain) throws IOException {
        Response originalResponse = chain.proceed(chain.request());
        return originalResponse.newBuilder()
                .body(new DownloadResponseBody(originalResponse.body(), listener))
                .build();
    }
}