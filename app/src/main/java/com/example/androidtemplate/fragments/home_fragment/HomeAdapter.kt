package com.example.androidtemplate.fragments.home_fragment

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ItemHomeBinding
import com.example.androidtemplate.util.GeneralFunctions
import com.example.androidtemplate.util.ItemClickListener

class HomeAdapter(
    private val context: Context,
    private var dashboardList: ArrayList<String>,
    private val itemClickListener: ItemClickListener
) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeBinding.bind(
                LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return dashboardList.size
    }

    inner class HomeViewHolder(private val mBinding: ItemHomeBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bindData() {
            mBinding.root.animation = GeneralFunctions.slideInBottomAnimation(context)
            mBinding.tvDemoTitle.text = dashboardList[adapterPosition]

            mBinding.root.setOnClickListener {
                itemClickListener.onItemClicked(this, adapterPosition)
            }
        }
    }

    fun changeAdapterData(dashboardList: ArrayList<String>) {
        this.dashboardList = dashboardList
        notifyDataSetChanged()
    }

}