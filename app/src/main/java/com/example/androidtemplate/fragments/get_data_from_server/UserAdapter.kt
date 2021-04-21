package com.example.androidtemplate.fragments.get_data_from_server

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ItemHomeBinding
import com.example.androidtemplate.util.GeneralFunctions

class UserAdapter : RecyclerView.Adapter<UserAdapter.HomeViewHolder>() {

    private var userList = arrayListOf<UsersResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemHomeBinding.bind(
                LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindData()
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    inner class HomeViewHolder(private val mBinding: ItemHomeBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bindData() {
            mBinding.root.animation =
                GeneralFunctions.provideAnimation(mBinding.root.context, R.anim.fall_down)
            mBinding.tvDemoTitle.text = userList[adapterPosition].title
        }
    }

    fun changeAdapterData(userList: ArrayList<UsersResponse>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}