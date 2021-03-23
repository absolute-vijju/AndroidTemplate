package com.example.androidtemplate.hilt

import com.example.androidtemplate.api.ApiClient
import com.example.androidtemplate.api.WebServices
import com.example.androidtemplate.fragments.get_data_from_server.UsersRepo
import com.example.androidtemplate.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun getString() = "Test string"

    @Singleton
    @Provides
    fun getService(): WebServices {
        if (ApiClient.retrofit != null)
            return ApiClient.retrofit!!.create(WebServices::class.java)

        val okHttpClient =
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
        ApiClient.retrofit = Retrofit.Builder().client(okHttpClient.build())
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return ApiClient.retrofit!!.create(WebServices::class.java)
    }

    @Singleton
    @Provides
    fun provideTestRepo() = UsersRepo()
}