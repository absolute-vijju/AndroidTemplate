package com.example.androidtemplate.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ActivityMainBinding
import com.example.androidtemplate.repo.TestRepo
import com.example.androidtemplate.util.Constants
import com.example.androidtemplate.util.Preference
import com.example.androidtemplate.util.showShortToast
import com.example.androidtemplate.viewmodel.TestViewModel
import com.google.gson.Gson
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding
    private lateinit var testViewModel: TestViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)

        testViewModel = TestViewModel(TestRepo())
        testViewModel.callTestApiFromViewModel()

        testViewModel.response.observe(this, Observer {
            showShortToast("Got response")
        })

        checkTheme()
        activityMainBinding.header.ivTheme.setOnClickListener {
            changeTheme()
        }

    }

    private fun checkTheme() {
        if (Preference.getBooleanData(this, Constants.IS_NIGHT_THEME)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            activityMainBinding.header.ivTheme.setImageResource(R.drawable.ic_light_mode)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            activityMainBinding.header.ivTheme.setImageResource(R.drawable.ic_dark_mode)
        }
    }

    private fun changeTheme() {
        if (Preference.getBooleanData(this, Constants.IS_NIGHT_THEME))
            Preference.putData(this, Constants.IS_NIGHT_THEME, false)
        else
            Preference.putData(this, Constants.IS_NIGHT_THEME, true)
        checkTheme()
    }


}