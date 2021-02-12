package com.example.androidtemplate.fragments.check_app_installed

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidtemplate.util.GeneralFunctions

class CheckAppViewModel : ViewModel() {

    val status = MutableLiveData<Boolean>()

    fun checkAppInstalledOrNot(context: Context, packageName: String) {
        status.value = GeneralFunctions.isAppInstalled(context, packageName)
    }

}