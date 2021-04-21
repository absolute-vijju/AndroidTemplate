package com.example.androidtemplate.fragments.diff_util

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ItemHomeBinding
import com.example.androidtemplate.util.GeneralFunctions

class PersonAdapter : RecyclerView.Adapter<PersonAdapter.PersonViewHolder>() {

    private var oldPersonList = emptyList<Person>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder {
        return PersonViewHolder(ItemHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        holder.apply {
            mBinding.root.animation = GeneralFunctions.provideAnimation(holder.mBinding.root.context, R.anim.fall_down)
            mBinding.tvDemoTitle.text =
                oldPersonList[position].name.plus("\n").plus(oldPersonList[position].number)
        }
    }

    override fun getItemCount(): Int {
        return oldPersonList.size
    }

    fun setData(newPersonList: List<Person>) {
        val diffUtil = MyDiffUtil(oldPersonList, newPersonList)
        val diffUtilResult = DiffUtil.calculateDiff(diffUtil)
        oldPersonList = newPersonList
        diffUtilResult.dispatchUpdatesTo(this)
    }

    class PersonViewHolder(val mBinding: ItemHomeBinding) : RecyclerView.ViewHolder(mBinding.root)
}