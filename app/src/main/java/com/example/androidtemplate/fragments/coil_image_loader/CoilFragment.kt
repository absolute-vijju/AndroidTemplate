package com.example.androidtemplate.fragments.coil_image_loader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentCoilBinding
import com.example.androidtemplate.util.*

class CoilFragment : BaseFragment() {

    private lateinit var mBinding: FragmentCoilBinding;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCoilBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        mBinding.ivCoil.load(Constants.IMAGE_URL) {
            crossfade(true)     // crossfade animation
            crossfade(2000)
            placeholder(R.drawable.ic_placeholder)  // replace with your placeholder image
            error(R.drawable.ic__error)     // when url failed to load, this image will show
            transformations(
                GrayscaleTransformation(),
                CircleCropTransformation(),
            )
        }
    }

    override fun setListener() {
        //TODO
    }
}