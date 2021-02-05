package com.example.androidtemplate.util

import android.app.Activity
import android.content.pm.PackageManager

object GeneralFunctions {
    fun isAppInstalled(activity: Activity, packageName: String): Boolean {
        val pm: PackageManager = activity.packageManager
        return try {
            pm.getPackageInfo(packageName, PackageManager.GET_ACTIVITIES)
            true
        } catch (e: PackageManager.NameNotFoundException) {
            false
        }
    }
}