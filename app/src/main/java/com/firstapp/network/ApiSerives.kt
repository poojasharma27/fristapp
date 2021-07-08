package com.firstapp.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiSerives {
companion object {
    fun getRetrofitInstance(): Retrofit {
        var logging = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)


        var httpClient = OkHttpClient.Builder().addInterceptor(logging)

        val retrofit =
            Retrofit.Builder().baseUrl("https://newsapi.org/v2/").addConverterFactory(
                GsonConverterFactory.create())
                .client(httpClient.build())
                .build()

        return retrofit

        // val api :ApiServiceIn = retrofit.create(ApiServiceIn::class.java)
    }
}
}