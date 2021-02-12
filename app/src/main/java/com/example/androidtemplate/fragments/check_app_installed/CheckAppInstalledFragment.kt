package com.example.androidtemplate.fragments.check_app_installed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentCheckAppInstalledBinding
import com.example.androidtemplate.util.BaseFragment

class CheckAppInstalledFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mBinding: FragmentCheckAppInstalledBinding
    private lateinit var viewModel: CheckAppViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCheckAppInstalledBinding.inflate(inflater)
        viewModel = ViewModelProvider(this).get(CheckAppViewModel::class.java)
        return mBinding.root
    }

    override fun init() {
        viewModel.status.observe(this, Observer {
            if (it)
                mBinding.tvStatus.text = mBinding.etPackageName.text.toString().plus(" ")
                    .plus(getString(R.string.installed))
            else
                mBinding.tvStatus.text = mBinding.etPackageName.text.toString().plus(" ")
                    .plus(getString(R.string.not_installed))
        })
    }

    override fun setListener() {
        mBinding.btnCheck.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        viewModel.checkAppInstalledOrNot(
            requireContext(),
            mBinding.etPackageName.text.toString()
        )
    }
}