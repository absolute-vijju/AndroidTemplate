package com.example.androidtemplate.fragments.diff_util

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtemplate.databinding.FragmentDiffUtilBinding
import com.example.androidtemplate.util.BaseFragment

class DiffUtilFragment : BaseFragment() {

    private lateinit var mBinding: FragmentDiffUtilBinding
    private val personAdapter by lazy { PersonAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDiffUtilBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        mBinding.rvPerson.layoutManager = LinearLayoutManager(requireContext())
        mBinding.rvPerson.adapter = personAdapter

        val person1 = Person(1, "Vijay", "9876543210")
        val person2 = Person(2, "Hardik", "1234567890")
        val person3 = Person(3, "Vraj", "9876543210")
        personAdapter.setData(listOf(person1, person2, person3))

        mBinding.btnAddNewItem.setOnClickListener {
            val person4 = Person(4, "Dharmik", "1234567890")
            personAdapter.setData(listOf(person1, person2, person3, person4))
        }
    }

    override fun setListener() {
    }
}