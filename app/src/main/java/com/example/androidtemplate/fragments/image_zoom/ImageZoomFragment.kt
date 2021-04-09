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
    private var scaleFactor = 1f

    private var xDown = 0f
    private var yDown = 0f

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
            crossfade(true)     // crossfade animation
            crossfade(2000)
            placeholder(R.drawable.ic_placeholder)  // replace with your placeholder image
            error(R.drawable.ic__error)     // when url failed to load, this image will show
        }
    }

    override fun setListener() {
        mBinding.root.setOnTouchListener { v, event ->
            scaleGestureDetector?.onTouchEvent(event)
        }
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector?): Boolean {
            scaleFactor *= scaleGestureDetector.scaleFactor
            mBinding.ivZoom.scaleX = scaleFactor
            mBinding.ivZoom.scaleY = scaleFactor
            return true
        }
    }
}