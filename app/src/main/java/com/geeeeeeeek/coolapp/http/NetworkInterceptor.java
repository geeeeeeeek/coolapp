package com.geeeeeeeek.coolapp.http;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class NetworkInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {

        String sid = "CAKFynDDuxQqWqPPhLDDGzfLXRFyNEpq";

        Request request = chain.request();

        Request newRequest = request
                .newBuilder()
                .url(request
                        .url()
                        .newBuilder()
                        .addQueryParameter("sid", sid)
                        .build()
                        .url())
                .build();

        return chain.proceed(newRequest);
    }
}
