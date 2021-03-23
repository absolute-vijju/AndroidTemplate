package com.example.androidtemplate.fragments.get_data_from_server

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtemplate.api.WebServices
import com.example.androidtemplate.databinding.FragmentGetServerDataBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.GeneralFunctions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GetServerDataFragment : BaseFragment() {

    @Inject
    lateinit var webServices: WebServices

    private lateinit var mBinding: FragmentGetServerDataBinding

    private val usersViewModel: UsersViewModel by viewModels()

    private lateinit var userAdapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentGetServerDataBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        mBinding.rvUsers.layoutManager = LinearLayoutManager(requireContext())
        userAdapter = UserAdapter(requireContext(), arrayListOf())
        mBinding.rvUsers.adapter = userAdapter

        if (GeneralFunctions.hasInternetConnected(requireContext())) {
            usersViewModel.callTestApiFromViewModel()
            usersViewModel.response.observe(this, {
                userAdapter.changeAdapterData(it)
            })
        }
    }

    override fun setListener() {
    }
}