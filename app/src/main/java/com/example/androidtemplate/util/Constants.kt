package com.example.androidtemplate.util

class Constants {
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
        const val IMAGE_URL =
            "https://avatars.githubusercontent.com/u/53289959?v=4"
        const val XML_PARSING_URL = "https://stackoverflow.com/feeds"

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