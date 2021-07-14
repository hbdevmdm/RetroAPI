package com.dc.mvvmskeleton.data.remote


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PlainApiClient {

    fun getService(): PlainApiService {

        val okHttpClient = OkHttpClient()
                .newBuilder()
                .addInterceptor(HttpLoggingInterceptor())
                .build()

        return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(PlainApiService::class.java)
    }
}
