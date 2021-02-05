package com.example.androidtemplate.activities

import android.os.Bundle
import com.example.androidtemplate.R
import com.example.androidtemplate.util.GeneralFunctions
import com.example.androidtemplate.util.showShortToast
import timber.log.Timber

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        Timber.d("This is log")
        Timber.d(GeneralFunctions.isAppInstalled(this, "com.whatsapp").toString())
        showShortToast(GeneralFunctions.isAppInstalled(this, "com.whatsapp").toString())
    }
}