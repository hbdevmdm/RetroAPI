package com.dc.mvvmskeleton.data.remote


import com.dc.retroapi.builder.RetrofitClientBuilder
import com.dc.retroapi.interceptor.EncryptionStrategy
import com.dc.retroapi.interceptor.RequestInterceptor
import com.dc.retroapi.interceptor.ResponseInterceptor

import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory


/**
 * Created by HB on 21/6/19.
 */

class ApiClient {
    companion object {
        private const val BASE_URL = "http://18.138.121.247/WS/"
        val apiService: ApiService by lazy {
            return@lazy RetrofitClientBuilder()
                    .baseUrl(BASE_URL)
                    .connectionTimeout(30)
                    .readTimeout(30)
                    .addInterceptor(RequestInterceptor(object : RequestInterceptor.OnRequestInterceptor {
                        override fun provideBodyMap(): HashMap<String, String> {
                            return HashMap()
                        }

                        override fun provideHeaderMap(): HashMap<String, String> {
                            return HashMap()
                        }

                        override fun removeFromBody(): ArrayList<String> {
                            return ArrayList<String>()
                        }
                    }))
                    .addLogInterceptor(HttpLoggingInterceptor.Level.BODY)
                    .addInterceptor(ResponseInterceptor(object : ResponseInterceptor.OnResponseCallback {
                        override fun onNetworkResponse(response: Response) {

                        }
                    }))
                    .addEncryptionInterceptor("CIT@WS!", EncryptionStrategy.REQUEST_RESPONSE,
                            true, "ws_checksum",false,
                            excludeFromEncryption = arrayListOf("ws_token"))
                    .addConverterFactory(GsonConverterFactory.create())
                    .create(ApiService::class.java)
        }
    }

}


