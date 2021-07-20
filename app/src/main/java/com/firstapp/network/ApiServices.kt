package com.firstapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

class ApiServices {
companion object {
    fun createInstance(): Retrofit {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        val httpClient = OkHttpClient.Builder().addInterceptor(logging) .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS);

        return Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()

        // val api :ApiServiceIn = retrofit.create(ApiServiceIn::class.java)
    }
}
}