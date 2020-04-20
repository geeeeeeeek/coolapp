package com.geeeeeeeek.coolapp.http;


import android.util.Log;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okio.Buffer;

import static java.nio.charset.StandardCharsets.UTF_8;


/**
  * @description: 基于Retrofit的网络请求框架(打印请求参数封装)
  */
class RetrofitLogInterceptor implements Interceptor {

   @Override
   public synchronized okhttp3.Response intercept(Chain chain) throws IOException {
       Request request = chain.request();
       long startTime = System.currentTimeMillis();
       okhttp3.Response response = chain.proceed(chain.request());
       long endTime = System.currentTimeMillis();
       long duration = endTime - startTime;
       okhttp3.MediaType mediaType = response.body().contentType();
       String content = response.body().string();
       Log.w("RetrofitLogInterceptor", "请求地址：| " + request.toString());
       printParams(request.body());
       Log.w("RetrofitLogInterceptor","请求体返回：| Response:" + content);
       return response.newBuilder().body(okhttp3.ResponseBody.create(mediaType, content)).build();
   }


   private void printParams(RequestBody body) {
       Buffer buffer = new Buffer();
       try {
           body.writeTo(buffer);
           Charset charset = Charset.forName("UTF-8");
           MediaType contentType = body.contentType();
           if (contentType != null) {
               charset = contentType.charset(UTF_8);
           }
           String params = buffer.readString(charset);
           Log.w("RetrofitLogInterceptor","请求参数： | " + params);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
