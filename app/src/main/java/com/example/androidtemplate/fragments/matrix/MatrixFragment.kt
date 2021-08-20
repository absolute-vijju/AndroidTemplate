package com.example.androidtemplate.fragments.matrix

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidtemplate.databinding.FragmentMatrixBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.showShortToast

class MatrixFragment : BaseFragment() {

    private lateinit var mBinding: FragmentMatrixBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMatrixBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {

    }

    override fun setListener() {
        mBinding.btnCreate.setOnClickListener {
            createMatrix()
        }
    }

    private fun createMatrix() {
        if (mBinding.etRow.text.toString().isEmpty()) {
            requireContext().showShortToast("Enter row")
            return
        }
        if (mBinding.etColumn.text.toString().isEmpty()) {
            requireContext().showShortToast("Enter column")
            return
        }


        // GET THE MATRIX DIMENSIONS
        val rows = mBinding.etRow.text.toString().toInt()
        val columns = mBinding.etColumn.text.toString().toInt()


        // CREATE A LIST OF MATRIX OBJECT
        val matrixList = arrayListOf<Matrix>()

        // ADD SOME CONTENTS TO EACH ITEM
        for (i in 0 until rows) {
            for (j in 0 until columns) {
                matrixList.add(Matrix(i, j))
            }
        }

        // CREATE AN ADAPTER  (MATRIX ADAPTER)
        val matrixAdapter = MatrixAdapter(requireContext(), matrixList)

        // ATTACH THE ADAPTER TO GRID
        // INITIALISE YOUR GRID
        mBinding.rvMatrix.apply {
            layoutManager = GridLayoutManager(context, columns)
            adapter = matrixAdapter
        }
    }
}