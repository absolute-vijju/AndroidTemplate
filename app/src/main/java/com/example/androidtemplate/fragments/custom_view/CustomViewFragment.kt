package com.example.androidtemplate.fragments.custom_view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtemplate.databinding.FragmentCustomViewBinding
import com.example.androidtemplate.util.BaseFragment

/**
 *  You need to create multiple custom view and show in adapter in this fragment
 */
class CustomViewFragment : BaseFragment() {

    private lateinit var mBinding: FragmentCustomViewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentCustomViewBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        //
    }

    override fun setListener() {
        //
    }
}