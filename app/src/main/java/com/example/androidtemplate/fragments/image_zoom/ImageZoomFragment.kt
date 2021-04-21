package com.example.androidtemplate.fragments.image_zoom

import android.os.Bundle
import android.view.*
import coil.load
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentImageZoomBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.Constants

class ImageZoomFragment : BaseFragment() {

    private lateinit var mBinding: FragmentImageZoomBinding
    private lateinit var scaleGestureDetector: ScaleGestureDetector
    private var mScaleFactor = 1f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentImageZoomBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {

        scaleGestureDetector = ScaleGestureDetector(requireContext(), ScaleListener())

        mBinding.ivZoom.load(Constants.IMAGE_URL) {
            crossfade(true)
            crossfade(2000)
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic__error)
        }
    }

    override fun setListener() {
        mBinding.root.setOnTouchListener { v, event ->
            scaleGestureDetector?.onTouchEvent(event)
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            mScaleFactor *= scaleGestureDetector.scaleFactor
            mBinding.ivZoom.scaleX = mScaleFactor
            mBinding.ivZoom.scaleY = mScaleFactor
            return true
        }
    }
}