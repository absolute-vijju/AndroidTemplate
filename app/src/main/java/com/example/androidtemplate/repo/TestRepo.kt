package com.example.androidtemplate.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidtemplate.api.WebServices
import com.example.androidtemplate.model.response.TestResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class TestRepo() {
    fun callApiFromRepo(webServices: WebServices): LiveData<ArrayList<TestResponse>> {

        val apiResponse = MutableLiveData<ArrayList<TestResponse>>()

        webServices.getData().enqueue(object : Callback<ArrayList<TestResponse>?> {
            override fun onResponse(
                call: Call<ArrayList<TestResponse>?>,
                response: Response<ArrayList<TestResponse>?>
            ) {
                if (response.isSuccessful)
                    apiResponse.value = response.body()
            }

            override fun onFailure(call: Call<ArrayList<TestResponse>?>, t: Throwable) {
                Timber.d("Error: ${t.message}")
            }
        })

        return apiResponse
    }
}