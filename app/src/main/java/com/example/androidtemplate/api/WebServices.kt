package com.example.androidtemplate.api

import androidx.lifecycle.LiveData
import com.example.androidtemplate.model.request.TestRequest
import com.example.androidtemplate.model.response.TestResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET

interface WebServices {
    @GET("posts")
    fun getData(): Call<ArrayList<TestResponse>>
}