package com.example.androidtemplate.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewModelFragmentViewModel : ViewModel() {
    var count = MutableLiveData<Int>(0)

    fun incrementByOne() {
        count.value = count.value?.plus(1)
    }
}