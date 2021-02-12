package com.example.androidtemplate.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.Observer
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ActivityMainBinding
import com.example.androidtemplate.repo.TestRepo
import com.example.androidtemplate.util.Constants
import com.example.androidtemplate.util.Preference
import com.example.androidtemplate.util.showShortToast
import com.example.androidtemplate.viewmodel.TestViewModel

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
    }

}