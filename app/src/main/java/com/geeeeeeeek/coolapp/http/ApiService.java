package com.geeeeeeeek.coolapp.http;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.Streaming;

public interface ApiService {


    @POST("api?func=mbox:listAttachments")
    Observable<BaseData> listAttachments(@Body RequestBody body);

    @Streaming
//    @GET("api?func=nf:packFiles")
    @GET("/F/E/B/2_net19880504_1550040974.jpg")
    Call<ResponseBody> downloadFile(@Query("mid") String mid, @Query("encoding") String encoding);

}
