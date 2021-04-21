package com.example.androidtemplate.hilt

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
        var retrofit: Retrofit? = null
        if (retrofit != null)
            return retrofit.create(WebServices::class.java)

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

    @Singleton
    @Provides
    fun provideUserRepo() = UsersRepo()
}