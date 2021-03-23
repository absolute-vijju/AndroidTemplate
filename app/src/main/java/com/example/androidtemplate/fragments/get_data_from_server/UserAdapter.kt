package com.example.androidtemplate.fragments.get_data_from_server

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ItemHomeBinding

class UserAdapter(private val context: Context, private var userList: ArrayList<UsersResponse>) : RecyclerView.Adapter<UserAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemHomeBinding.bind(LayoutInflater.from(context).inflate(R.layout.item_home, parent, false)))
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
            mBinding.tvDemoTitle.text = userList[adapterPosition].title
        }
    }

    fun changeAdapterData(userList: ArrayList<UsersResponse>) {
        this.userList = userList
        notifyDataSetChanged()
    }

}