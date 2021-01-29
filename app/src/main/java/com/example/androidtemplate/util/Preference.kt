package com.example.androidtemplate.util

import android.content.Context
import android.content.SharedPreferences

class Preference {
    companion object {
        private fun getInstance(context: Context): SharedPreferences {
            return context.getSharedPreferences(Constants.PREF_NAME, Context.MODE_PRIVATE)
        }

        fun putData(context: Context, key: String, value: String) {
            val mEditor = getInstance(context).edit()
            mEditor.putString(key, value)
            mEditor.apply()
        }

        fun putData(context: Context, key: String, value: Boolean) {
            val mEditor = getInstance(context).edit()
            mEditor.putBoolean(key, value)
            mEditor.apply()
        }

        fun getStringData(context: Context, key: String): String? {
            return getInstance(context).getString(key, "")
        }

        fun getBooleanData(context: Context, key: String): Boolean {
            return getInstance(context).getBoolean(key, false)
        }

        fun removeData(context: Context, key: String) {
            val mEditor = getInstance(context).edit()
            mEditor.remove(key)
            mEditor.apply()
        }
    }
}