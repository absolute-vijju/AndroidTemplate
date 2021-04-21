package com.example.androidtemplate.fragments.get_data_from_server

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtemplate.hilt.AppModule.getService

class UsersViewModel @ViewModelInject constructor(private val usersRepo: UsersRepo) : ViewModel() {

    var response = MutableLiveData<ArrayList<UsersResponse>>()

    fun callApiFromViewModel() {
        response = usersRepo.callApiFromRepo(getService()) as MutableLiveData<ArrayList<UsersResponse>>
    }
}