package com.example.androidtemplate.fragments.get_data_from_server

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.androidtemplate.api.WebServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UsersRepo() {
    fun callApiFromRepo(webServices: WebServices): LiveData<ArrayList<UsersResponse>> {

        val apiResponse = MutableLiveData<ArrayList<UsersResponse>>()

        CoroutineScope(Dispatchers.IO).launch {

            val response = webServices.getData()
            if (response.isSuccessful) {
                withContext(Dispatchers.Main) {
                    apiResponse.value = response.body()
                }
            }
        }
        return apiResponse
    }

    sealed class NetworkState {
        object Success : NetworkState()
        data class Error(val message: String) : NetworkState()
        object Loading : NetworkState()
    }
}