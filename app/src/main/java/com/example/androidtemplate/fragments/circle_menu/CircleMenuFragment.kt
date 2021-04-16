package com.example.androidtemplate.fragments.circle_menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentCircleMenuBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.showShortToast
import com.ramotion.circlemenu.CircleMenuView
import timber.log.Timber

class CircleMenuFragment : BaseFragment() {

    private lateinit var mBinding: FragmentCircleMenuBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentCircleMenuBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        //TODO
    }

    override fun setListener() {
        mBinding.circleMenu.eventListener = object : CircleMenuView.EventListener() {
            override fun onMenuOpenAnimationStart(view: CircleMenuView) {
                super.onMenuOpenAnimationStart(view)
                Timber.e("Circle-Menu: onMenuOpenAnimationStart")
            }

            override fun onMenuOpenAnimationEnd(view: CircleMenuView) {
                super.onMenuOpenAnimationEnd(view)
                Timber.e("Circle-Menu: onMenuOpenAnimationEnd")
            }

            override fun onMenuCloseAnimationStart(view: CircleMenuView) {
                super.onMenuCloseAnimationStart(view)
                Timber.e("Circle-Menu: onMenuCloseAnimationStart")
            }

            override fun onMenuCloseAnimationEnd(view: CircleMenuView) {
                super.onMenuCloseAnimationEnd(view)
                Timber.e("Circle-Menu: onMenuCloseAnimationEnd")
            }

            override fun onButtonClickAnimationStart(view: CircleMenuView, buttonIndex: Int) {
                super.onButtonClickAnimationStart(view, buttonIndex)
                Timber.e("Circle-Menu: onButtonClickAnimationStart")
                requireContext().showShortToast(buttonIndex.toString().trim())
            }

            override fun onButtonClickAnimationEnd(view: CircleMenuView, buttonIndex: Int) {
                super.onButtonClickAnimationEnd(view, buttonIndex)
                Timber.e("Circle-Menu: onButtonClickAnimationEnd")
            }

            override fun onButtonLongClick(view: CircleMenuView, buttonIndex: Int): Boolean {
                Timber.e("Circle-Menu: onButtonLongClick")
                return super.onButtonLongClick(view, buttonIndex)
            }

            override fun onButtonLongClickAnimationStart(view: CircleMenuView, buttonIndex: Int) {
                super.onButtonLongClickAnimationStart(view, buttonIndex)
                Timber.e("Circle-Menu: onButtonLongClickAnimationStart")
            }

            override fun onButtonLongClickAnimationEnd(view: CircleMenuView, buttonIndex: Int) {
                super.onButtonLongClickAnimationEnd(view, buttonIndex)
                Timber.e("Circle-Menu: onButtonLongClickAnimationEnd")
            }
        }
    }
}