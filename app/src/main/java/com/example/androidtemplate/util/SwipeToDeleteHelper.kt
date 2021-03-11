package com.example.androidtemplate.util

import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class SwipeToDeleteHelper(private val swipeToDelete: SwipeToDelete) :
    ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        swipeToDelete.onSwiped(viewHolder, direction)
    }

    interface SwipeToDelete {
        fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int)
    }

}