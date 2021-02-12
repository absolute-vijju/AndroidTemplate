package com.example.androidtemplate.util

import androidx.recyclerview.widget.RecyclerView

interface ItemClickListener {
    fun onItemClicked(viewHolder: RecyclerView.ViewHolder, viewId: Int?)
}