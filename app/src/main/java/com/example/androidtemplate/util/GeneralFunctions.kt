package com.example.androidtemplate.util

import android.app.DatePickerDialog
import android.content.Context
import android.content.pm.PackageManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.DatePicker
import com.example.androidtemplate.R
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset
import java.text.SimpleDateFormat
import java.util.*

object GeneralFunctions {
    fun isAppInstalled(context: Context, packageName: String): Boolean {
        val pm: PackageManager = context.packageManager
        return try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }

    fun loadJSONFromAsset(context: Context): String? {
        var json: String? = null
        json = try {
            val `is`: InputStream = context.assets.open("HomeDashboardData")
            val size: Int = `is`.available()
            val buffer = ByteArray(size)
            `is`.read(buffer)
            `is`.close()
            String(buffer, Charset.forName("UTF-8"))
        } catch (ex: IOException) {
            ex.printStackTrace()
            return null
        }
        return json
    }

    fun slideInBottomAnimation(context: Context): Animation {
        return AnimationUtils.loadAnimation(context, R.anim.slide_in_bottom).apply {
            duration = 300
        }
    }

    fun getDate(
        mContext: Context,
        dateLimitType: Constants.Companion.DateLimitType,
        dateSelectionListener: DateSelectionListener
    ) {
        val c = Calendar.getInstance()
        val mYear = c[Calendar.YEAR]
        val mMonth = c[Calendar.MONTH]
        val mDay = c[Calendar.DAY_OF_MONTH]
        val datePickerDialog = DatePickerDialog(
            mContext,
            { view, year, month, dayOfMonth ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, year)
                calendar.set(Calendar.MONTH, month)
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                dateSelectionListener.onDateSelected(calendar)
            }, mYear, mMonth, mDay
        )
        when {
            dateLimitType === Constants.Companion.DateLimitType.MAX -> {
                datePickerDialog.datePicker.maxDate = Date().time
            }
            dateLimitType === Constants.Companion.DateLimitType.MIN -> {
                datePickerDialog.datePicker.minDate = Date().time
            }
            dateLimitType === Constants.Companion.DateLimitType.NONE -> {
                // DO SOMETHING
            }
        }
        datePickerDialog.show()
    }

    fun formatDate(date: Date, format: String = Constants.DATE_FORMAT): String {
        return SimpleDateFormat(format).format(date)
    }

}