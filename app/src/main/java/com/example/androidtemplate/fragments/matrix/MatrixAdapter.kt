package com.example.androidtemplate.fragments.matrix

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.androidtemplate.databinding.ItemGridBinding

class MatrixAdapter(var context: Context, var matrixList: ArrayList<Matrix>) :
    RecyclerView.Adapter<MatrixAdapter.MatrixViewHolder>() {

    inner class MatrixViewHolder(val mBinding: ItemGridBinding) :
        RecyclerView.ViewHolder(mBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatrixViewHolder {
        return MatrixViewHolder(ItemGridBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: MatrixViewHolder, position: Int) {
        holder.apply {
            mBinding.tvMatrix.text="0"
        }
    }

    override fun getItemCount(): Int {
        return matrixList.size
    }
}