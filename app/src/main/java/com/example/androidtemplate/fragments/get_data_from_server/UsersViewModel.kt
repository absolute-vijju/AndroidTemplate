package com.example.androidtemplate.fragments.get_data_from_server

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtemplate.api.ApiClient

class UsersViewModel @ViewModelInject constructor(private val usersRepo: UsersRepo) : ViewModel() {

    var response = MutableLiveData<ArrayList<UsersResponse>>()

    fun callTestApiFromViewModel() {
        response = usersRepo.callApiFromRepo(ApiClient.getService()) as MutableLiveData<ArrayList<UsersResponse>>
    }
}