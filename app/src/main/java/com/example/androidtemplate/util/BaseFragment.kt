package com.example.androidtemplate.util

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    abstract fun init()
    abstract fun setListener()

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        setListener()
    }
}