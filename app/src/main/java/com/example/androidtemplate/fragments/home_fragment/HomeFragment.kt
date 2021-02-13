package com.example.androidtemplate.fragments.home_fragment

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentHomeBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.GeneralFunctions
import com.example.androidtemplate.util.ItemClickListener
import com.example.androidtemplate.util.showShortToast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import timber.log.Timber
import kotlin.random.Random

class HomeFragment : BaseFragment() {

    private lateinit var mBinding: FragmentHomeBinding
    private lateinit var mContext: Activity
    private lateinit var homeAdapter: HomeAdapter

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
        manageShimmer(true)
        mBinding.rvHome.layoutManager = LinearLayoutManager(mContext)
        homeAdapter = HomeAdapter(mContext, arrayListOf(), object : ItemClickListener {
            override fun onItemClicked(viewHolder: RecyclerView.ViewHolder, viewId: Int?) {
                viewId?.let {
                    when (it) {
                        0 -> view?.findNavController()
                            ?.navigate(R.id.action_homeFragment_to_checkAppInstalledFragment)
                        else -> view?.findNavController()
                            ?.navigate(R.id.action_homeFragment_to_calenderRangeFragment)
                    }

                }
            }
        })
        mBinding.rvHome.adapter = homeAdapter

        val data = Gson().fromJson<ArrayList<String>>(
            GeneralFunctions.loadJSONFromAsset(mContext),
            object : TypeToken<ArrayList<String>>() {}.type
        )

        GlobalScope.launch {

            delay(1000)
            withContext(Dispatchers.Main) {
                manageShimmer(false)
                homeAdapter.changeAdapterData(data)
            }
        }

    }

    override fun setListener() {
    }

    private fun manageShimmer(showShimmer: Boolean) {
        if (showShimmer) {
            mBinding.shimmerLayout.visibility = View.VISIBLE
            mBinding.shimmerLayout.startShimmer()
        } else {
            mBinding.shimmerLayout.visibility = View.GONE
            mBinding.shimmerLayout.stopShimmer()
        }

    }
}