package com.example.androidtemplate.view.ui

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentViewModelBinding
import com.example.androidtemplate.viewmodel.ViewModelFragmentViewModel

class ViewModelFragment : Fragment() {

    private lateinit var fragmentViewModelBinding: FragmentViewModelBinding
    private lateinit var viewModelFragmentViewModel: ViewModelFragmentViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentViewModelBinding = FragmentViewModelBinding.inflate(inflater)
        return fragmentViewModelBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModelFragmentViewModel =
            ViewModelProvider(this).get(ViewModelFragmentViewModel::class.java)

        viewModelFragmentViewModel.count.observe(viewLifecycleOwner, Observer {
            fragmentViewModelBinding.tvCount.text = it.toString()
        })

        fragmentViewModelBinding.btnIncrementByOne.setOnClickListener {
            viewModelFragmentViewModel.incrementByOne()
        }
    }
}