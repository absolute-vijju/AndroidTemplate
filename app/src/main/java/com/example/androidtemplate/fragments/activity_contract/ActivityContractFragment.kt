package com.example.androidtemplate.fragments.activity_contract

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import com.example.androidtemplate.databinding.FragmentActivityContractBinding
import com.example.androidtemplate.util.BaseFragment
import com.example.androidtemplate.util.showShortToast
import timber.log.Timber

class ActivityContractFragment : BaseFragment() {

    private lateinit var mBinding: FragmentActivityContractBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentActivityContractBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        val imagePickerCallback =
            registerForActivityResult(ActivityResultContracts.GetContent()) { resultUri ->
                mBinding.ivProfile.setImageURI(resultUri)
            }


        val multiPermissionCallback =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { resultMap ->
                for (i in resultMap.keys) {
                    Timber.e("mydata: Keys: $i")
                }
                for (i in resultMap.values) {
                    Timber.e("mydata: Values: $i")
                }

                resultMap.entries.forEach { entry ->
                    when (entry.key) {
                        Manifest.permission.ACCESS_FINE_LOCATION -> {
                            if (entry.value)
                                mBinding.tvLocationPermissionStatus.text =
                                    "Location permission granted."
                            else
                                mBinding.tvLocationPermissionStatus.text =
                                    "Location permission NOT granted."
                        }
                        Manifest.permission.WRITE_CONTACTS -> {
                            if (entry.value)
                                mBinding.tvWriteContactPermissionStatus.text =
                                    "Contact permission granted."
                            else
                                mBinding.tvWriteContactPermissionStatus.text =
                                    "Contact permission NOT granted."
                        }
                    }
                }
            }

        mBinding.btnPickImage.setOnClickListener {
            imagePickerCallback.launch("image/*")
        }

        mBinding.btnTakePermission.setOnClickListener {
            multiPermissionCallback.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.WRITE_CONTACTS
                )
            )
        }
    }

    override fun setListener() {

    }
}