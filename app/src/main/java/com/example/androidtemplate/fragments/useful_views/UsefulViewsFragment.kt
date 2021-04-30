package com.example.androidtemplate.fragments.useful_views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtemplate.databinding.FragmentUsefulViewsBinding
import com.example.androidtemplate.util.BaseFragment

class UsefulViewsFragment : BaseFragment() {

    private lateinit var mBinding: FragmentUsefulViewsBinding
    private val viewsAdapter by lazy { ViewsAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentUsefulViewsBinding.inflate(inflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun init() {
        mBinding.rvUsefulViews.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvUsefulViews.adapter = viewsAdapter

        viewsAdapter.setData(listOf(""))
    }

    override fun setListener() {
        //
    }
}