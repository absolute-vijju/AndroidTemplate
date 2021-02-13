package com.example.androidtemplate.activities

import android.app.Dialog
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.example.androidtemplate.R

abstract class BaseActivity : AppCompatActivity() {
    private var dialog: Dialog? = null

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        dialog = Dialog(this, android.R.style.Widget_Material_ProgressBar)
    }

    fun showProgressbar() {
        dialog?.setContentView(R.layout.progress_bar_layout)
        dialog?.setCancelable(false)
        dialog?.show()
    }

    fun hideProgressbar() {
        dialog?.dismiss()
    }

}