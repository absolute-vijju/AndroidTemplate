package com.example.androidtemplate.model.response

import com.google.gson.annotations.SerializedName

import com.google.gson.annotations.Expose


data class TestResponse(
    @Expose
    @SerializedName("body")
    var body: String = "",
    @Expose
    @SerializedName("id")
    var id: Int = 0,
    @Expose
    @SerializedName("title")
    var title: String = "",
    @Expose
    @SerializedName("userId")
    var userId: Int = 0
)