package com.example.androidtemplate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtemplate.api.ApiClient
import com.example.androidtemplate.model.response.TestResponse
import com.example.androidtemplate.repo.TestRepo

class TestViewModel(private val testRepo: TestRepo) : ViewModel() {

    var response = MutableLiveData<ArrayList<TestResponse>>()

    fun callTestApiFromViewModel() {
        response = testRepo.callApiFromRepo(ApiClient.getService()) as MutableLiveData<ArrayList<TestResponse>>
    }
}