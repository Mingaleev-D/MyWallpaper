package com.example.mywallpaper.data.remote

import com.example.mywallpaper.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
   private val retrofit by lazy {
      val logging = HttpLoggingInterceptor()
      logging.setLevel(HttpLoggingInterceptor.Level.BODY)
      val client = OkHttpClient.Builder()
          .addInterceptor(logging)
          .build()
      Retrofit.Builder()
          .baseUrl(BuildConfig.BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .client(client)
          .build()
   }

   val api by lazy {
      retrofit.create(ApiService::class.java)
   }
}