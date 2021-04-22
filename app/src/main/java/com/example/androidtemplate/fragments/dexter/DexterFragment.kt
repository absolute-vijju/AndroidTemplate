package com.example.androidtemplate.fragments.dexter

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidtemplate.R
import com.example.androidtemplate.databinding.FragmentDexterBinding
import com.example.androidtemplate.util.BaseFragment
import com.google.android.material.snackbar.Snackbar
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionDeniedResponse
import com.karumi.dexter.listener.PermissionGrantedResponse
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.CompositeMultiplePermissionsListener
import com.karumi.dexter.listener.multi.DialogOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.karumi.dexter.listener.multi.SnackbarOnAnyDeniedMultiplePermissionsListener
import com.karumi.dexter.listener.single.CompositePermissionListener
import com.karumi.dexter.listener.single.DialogOnDeniedPermissionListener
import com.karumi.dexter.listener.single.SnackbarOnDeniedPermissionListener
import timber.log.Timber

class DexterFragment : BaseFragment(), View.OnClickListener {

    private lateinit var mBinding: FragmentDexterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentDexterBinding.inflate(inflater)
        return mBinding.root
    }

    override fun init() {
        //TODO
    }

    override fun setListener() {
        mBinding.btnSinglePermission.setOnClickListener(this)
        mBinding.btnMultiplePermission.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnSinglePermission -> {

                val permissionListener =
                    object : com.karumi.dexter.listener.single.PermissionListener {
                        override fun onPermissionGranted(p0: PermissionGrantedResponse?) {
                            Timber.e("Permission: Granted")
                            Timber.e("Permission: ${p0?.permissionName}")
                        }

                        override fun onPermissionDenied(p0: PermissionDeniedResponse?) {
                            Timber.e("Permission: Denied")
                        }

                        override fun onPermissionRationaleShouldBeShown(
                            p0: PermissionRequest?,
                            p1: PermissionToken?
                        ) {
                            // When you deny permission, next time while asking permission, this method will be call
                            Timber.e("Permission: Rationale")
                            p1?.continuePermissionRequest()
                        }

                    }

                val dialogPermissionListener = DialogOnDeniedPermissionListener.Builder
                    .withContext(requireContext())
                    .withTitle("Camera permission")
                    .withMessage("Camera permission is needed to take pictures of your cat")
                    .withButtonText(android.R.string.ok)
                    .withIcon(android.R.drawable.ic_dialog_alert)
                    .build()

                val snackBarPermissionListener = SnackbarOnDeniedPermissionListener.Builder
                    .with(mBinding.root, "Camera access is needed to take pictures of your dog")
                    .withOpenSettingsButton("open app settings")    // This will open app settings
                    .withCallback(object : Snackbar.Callback() {
                        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                            // Event handler for when the given Snackbar is visible
                            Timber.e("Permission: SnackBar Dismissed")
                        }

                        override fun onShown(sb: Snackbar?) {
                            // Event handler for when the given Snackbar has been dismissed
                            Timber.e("Permission: SnackBar Shown")
                        }
                    })
                    .build()

                val compositePermissionListener =
                    CompositePermissionListener(
                        permissionListener,
//                        dialogPermissionListener,
                        snackBarPermissionListener
                    )

                Dexter.withContext(requireContext())
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(compositePermissionListener)
                    .withErrorListener {
                        Timber.e("Permission: Error ${it.toString()}")
                    }
                    .check()
            }
            R.id.btnMultiplePermission -> {

                val permissionListener = object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                        if (p0?.areAllPermissionsGranted()!!)
                            Timber.e("Permission: Granted")

                        for (i in p0.grantedPermissionResponses) {   // Granted permission list
                            Timber.e("Permission: ${i.permissionName}")
                        }

                    }

                    override fun onPermissionRationaleShouldBeShown(
                        p0: MutableList<PermissionRequest>?,
                        p1: PermissionToken?
                    ) {
                        Timber.e("Permission: Rationale")
                        for (i in p0?.indices!!) {
                            Timber.e("Permission: ${p0[i].name}")
                        }
                        p1?.continuePermissionRequest()
                    }

                }

                val dialogPermissionListener = DialogOnAnyDeniedMultiplePermissionsListener.Builder
                    .withContext(requireContext())
                    .withTitle("Camera & audio permission")
                    .withMessage("Both camera and audio permission are needed to take pictures of your cat")
                    .withButtonText(android.R.string.ok)
                    .withIcon(android.R.drawable.ic_dialog_alert)
                    .build()

                val snackBarPermissionListener =
                    SnackbarOnAnyDeniedMultiplePermissionsListener.Builder
                        .with(
                            mBinding.root,
                            "Both camera and audio permission are needed to take pictures of your cat"
                        )
                        .withOpenSettingsButton("open app settings")    // This will open app settings
                        .withCallback(object : Snackbar.Callback() {
                            override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
                                // Event handler for when the given Snackbar is visible
                                Timber.e("Permission: SnackBar Dismissed")
                            }

                            override fun onShown(sb: Snackbar?) {
                                // Event handler for when the given Snackbar has been dismissed
                                Timber.e("Permission: SnackBar Shown")
                            }
                        })
                        .build()

                val compositePermissionListener =
                    CompositeMultiplePermissionsListener(
                        permissionListener,
//                        dialogPermissionListener,
                        snackBarPermissionListener
                    )

                Dexter.withContext(requireContext())
                    .withPermissions(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
                    .withListener(compositePermissionListener)
                    .withErrorListener {
                        Timber.e("Permission: Error ${it.toString()}")
                    }
                    .check()

            }
        }
    }

}