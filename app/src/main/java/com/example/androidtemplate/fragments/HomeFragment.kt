package com.example.androidtemplate.fragments

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentHomeBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.showShortToast
import timber.log.Timber

class HomeFragment : BaseFragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mContext: Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentHomeBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        Timber.d("Fragment Init")
    }

    override fun setListener() {
        Timber.d("Fragment Listener")
    }
}