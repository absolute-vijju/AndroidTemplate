package com.example.androidtemplate.api

import com.example.androidtemplate.util.Constants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        var retrofit: Retrofit? = null
        fun getService(): WebServices {
            if (retrofit != null)
                return retrofit!!.create(WebServices::class.java)

            val okHttpClient =
                OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
            retrofit = Retrofit.Builder().client(okHttpClient.build())
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit!!.create(WebServices::class.java)
        }
    }
}