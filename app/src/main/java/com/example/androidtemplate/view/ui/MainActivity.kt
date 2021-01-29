package com.example.androidtemplate.view.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ActivityMainBinding
import com.example.androidtemplate.util.Constants
import com.example.androidtemplate.util.Preference

class MainActivity : AppCompatActivity() {

    private lateinit var activityMainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityMainBinding.root)


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