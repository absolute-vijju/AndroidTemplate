package com.example.androidtemplate.fragments.diff_util

import androidx.recyclerview.widget.DiffUtil

class MyDiffUtil(
    private val oldPersonList: List<Person>,
    private val newPersonList: List<Person>,
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldPersonList.size
    }

    override fun getNewListSize(): Int {
        return newPersonList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldPersonList[oldItemPosition].id == newPersonList[newItemPosition].id   // Compare with unique field
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if (oldPersonList[oldItemPosition].id != newPersonList[newItemPosition].id)
            return false
        if (oldPersonList[oldItemPosition].name != newPersonList[newItemPosition].name)
            return false
        if (oldPersonList[oldItemPosition].number != newPersonList[newItemPosition].number)
            return false
        return true
    }
}