package com.example.androidtemplate.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.findNavController
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.ActivityHomeBinding
import com.example.androidtemplate.util.Constants
import com.example.androidtemplate.util.GeneralFunctions
import com.example.androidtemplate.util.Preference
import com.example.androidtemplate.util.showShortToast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import timber.log.Timber
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset

class HomeActivity : BaseActivity() {

    private lateinit var mBinding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        val navController = findNavController(R.id.navHostFragment)
        navController?.addOnDestinationChangedListener { controller, destination, arguments ->
            when (destination.id) {
                R.id.homeFragment -> mBinding.header.tvTitle.text = getString(R.string.app_name)
                R.id.checkAppInstalledFragment -> mBinding.header.tvTitle.text = getString(R.string.check_app_availability)
                R.id.calenderRangeFragment -> mBinding.header.tvTitle.text = getString(R.string.calender_range)
            }
        }

        checkTheme()
        mBinding.header.ivTheme.setOnClickListener {
            changeTheme()
        }
    }

    private fun checkTheme() {
        if (Preference.getBooleanData(this, Constants.IS_NIGHT_THEME)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            mBinding.header.ivTheme.setImageResource(R.drawable.ic_light_mode)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            mBinding.header.ivTheme.setImageResource(R.drawable.ic_dark_mode)
        }
    }

    private fun changeTheme() {
        if (Preference.getBooleanData(this, Constants.IS_NIGHT_THEME))
            Preference.putData(this, Constants.IS_NIGHT_THEME, false)
        else
            Preference.putData(this, Constants.IS_NIGHT_THEME, true)
        checkTheme()
    }

}