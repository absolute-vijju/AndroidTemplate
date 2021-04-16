package com.example.androidtemplate.util

class Constants {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val IMAGE_URL =
            "https://images.unsplash.com/photo-1583089892943-e02e5b017b6a?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=80"

        //Permission & Activity Result code
        const val READ_AND_WRITE_CONTACTS = 1

        const val DATE_FORMAT = "dd-MM-yyyy"
        const val DAY_FORMAT = "dd MMM"
        const val WEEK_FORMAT = "dd MMM"
        const val MONTH_FORMAT = "MMM"

        const val PREF_NAME = "android_template"
        const val IS_NIGHT_THEME = "is_night_theme"

        enum class DateLimitType {
            MAX, MIN, NONE
        }

        enum class CalenderAdapterType {
            DAY, WEEK, MONTH
        }
    }
}