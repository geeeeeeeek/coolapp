package com.geeeeeeeek.coolapp.http;

import java.io.IOException;
import java.util.HashSet;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by XiaoQingsong
 * Date: 2020/4/16
 * Time: 3:53 PM
 */
public class AddCookieInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        String ssid = "6579101c-0f43-4137-ad99-50763e02e19d";
        String coremail = "9800d66910f431e308a937e3858fce31";
        String coremailsid = "";

        Request.Builder builder = chain.request().newBuilder();
        builder.addHeader("Cookie", "ssid=" + ssid);
        builder.addHeader("Cookie", "Coremail=" + coremail);
        builder.addHeader("Cookie", "Coremail.sid=" + coremailsid);
        return chain.proceed(builder.build());
    }
}
