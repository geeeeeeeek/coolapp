package com.geeeeeeeek.coolapp.http


import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

object ApiClient {
    private val TAG = "ApiClient"
    private var sAPIService: ApiService? = null

    val apiService: ApiService
        get() {

            if (null == sAPIService) {
                val logging = CustomLoggingInterceptor()
                logging.level = CustomLoggingInterceptor.Level.BODY

                val okhttpClient = OkHttpClient.Builder()
                        .addInterceptor(NetworkInterceptor())
                        .addInterceptor(AddCookieInterceptor())
                        .addInterceptor(logging)
                        .connectTimeout(30, TimeUnit.SECONDS)
                        .writeTimeout(30, TimeUnit.SECONDS)
                        .readTimeout(30, TimeUnit.SECONDS)
                        .build()
                sAPIService = createRetrofit(APIConfig.API_BASE_URL, okhttpClient).create(ApiService::class.java)
            }
            return sAPIService!!
        }


    private fun createRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(MailConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
    }
}
