package com.firstapp.network

import com.firstapp.network.model.NewsResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiServiceIn {

  /*  @POST("auth/login")
  fun signIn(@Query("mobile") mobile: String,@Query("password") password : String): Call<LoginResponce>
*/
  @GET("everything")
  fun news(@Query("q") q: String, @Query("from") from : Int, @Query("sortBy") sortBy : String, @Query("apiKey") apiKey : String): Call<NewsResponse>


}