package com.example.androidtemplate.fragments.get_data_from_server

import com.google.gson.annotations.SerializedName

import com.google.gson.annotations.Expose


data class UsersResponse(
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