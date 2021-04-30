package com.example.androidtemplate.fragments.useful_views

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.databinding.ItemWishlistBinding
import com.example.androidtemplate.util.showShortToast

class ViewsAdapter : RecyclerView.Adapter<ViewsAdapter.ViewViewHolder>() {

    private var viewsList = emptyList<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewViewHolder {
        return ViewViewHolder(
            ItemWishlistBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewViewHolder, position: Int) {
        holder.apply {
            mBinding.cbHeart.setOnCheckedChangeListener { checkBox, isChecked ->

                if (isChecked) {
                    itemView.context.showShortToast("Item added to Wishlist")
                } else {
                    itemView.context.showShortToast("Item removed from Wishlist")
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return viewsList.size
    }

    fun setData(newViewsList: List<String>) {
        viewsList = newViewsList
        notifyDataSetChanged()
    }

    class ViewViewHolder(val mBinding: ItemWishlistBinding) : RecyclerView.ViewHolder(mBinding.root)
}