package com.firstapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiServices {
companion object {
    fun getRetrofitInstance(): Retrofit {
        val logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        val httpClient = OkHttpClient.Builder().addInterceptor(logging)

        return Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(MoshiConverterFactory.create())
            .client(httpClient.build())
            .build()

        // val api :ApiServiceIn = retrofit.create(ApiServiceIn::class.java)
    }
}
}