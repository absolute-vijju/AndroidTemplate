package com.example.androidtemplate.util

import androidx.recyclerview.widget.RecyclerView

interface ItemClickListener {
    fun onItemClicked(viewHolder: RecyclerView.ViewHolder, position: Int, viewId: Int?)
}