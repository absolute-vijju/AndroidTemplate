package com.example.androidtemplate.api

import com.example.androidtemplate.fragments.get_data_from_server.UsersResponse
import retrofit2.Response
import retrofit2.http.GET

interface WebServices {
    @GET("posts")
    suspend fun getData(): Response<ArrayList<UsersResponse>>
}