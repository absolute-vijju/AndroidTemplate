package com.example.androidtemplate.util

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.example.androidtemplate.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL
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

    fun provideAnimation(context: Context, animationId: Int): Animation {
        return AnimationUtils.loadAnimation(context, animationId).apply {
            duration = 400
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

    fun disableScreenShot(activity: Activity) {
        activity.window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        )
    }

    private fun hasNetworkAvailable(context: Context): Boolean {
        val service = Context.CONNECTIVITY_SERVICE
        val manager = context.getSystemService(service) as ConnectivityManager?
        val network = manager?.activeNetworkInfo
        return (network != null)
    }

    fun hasInternetConnected(context: Context): Boolean {
        var status = false
        if (hasNetworkAvailable(context)) {
            CoroutineScope(Dispatchers.IO).launch {
                val connection =
                    URL("https://www.google.com").openConnection() as HttpURLConnection
                try {
                    connection.setRequestProperty("User-Agent", "ConnectionTest")
                    connection.setRequestProperty("Connection", "close")
                    connection.connectTimeout = 2000
                    connection.connect()
                    if (connection.responseCode == 200)
                        status = true
                } catch (e: IOException) {
                    withContext(Dispatchers.Main) {
                        showNetworkError(
                            context,
                            context.getString(R.string.please_make_sure_your_wifi_or_mobile_data_have_internet_connection)
                        )
                    }
                } finally {
                    connection.disconnect()
                }
            }
        } else {
            showNetworkError(context, context.getString(R.string.no_network_available))
        }
        return status
    }


    private fun showNetworkError(context: Context, message: String) {
        AlertDialog.Builder(context, android.R.style.Theme_Material_Light_Dialog_Alert)
            .setTitle(context.getString(R.string.no_internet_connection))
            .setMessage(message)
            .setCancelable(false)
            .setPositiveButton("Ok") { dialog, which ->
                dialog.dismiss()
                context.startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
            .show()
    }
}