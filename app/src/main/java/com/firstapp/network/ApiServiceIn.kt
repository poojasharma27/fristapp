package com.firstapp.network

import com.firstapp.model.YourNewsResponse
import retrofit2.Call
import org.json.JSONObject
import retrofit2.http.*


interface ApiServiceIn {

  /*  @POST("auth/login")
  fun signIn(@Query("mobile") mobile: String,@Query("password") password : String): Call<LoginResponce>
*/
  @GET("everything")
  fun newsresponse(@Query("q") q: String,@Query("from") from : Int,@Query("sortBy") sortby : String,@Query("apiKey") apiKey : String): Call<YourNewsResponse>


}