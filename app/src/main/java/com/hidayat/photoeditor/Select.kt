package com.hidayat.photoeditor

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.github.dhaval2404.imagepicker.ImagePicker
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener


class Select : AppCompatActivity() {

    private var uri: Uri? = null
    private lateinit var tv: TextView
    private lateinit var tv1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select)

        tv = findViewById(R.id.textView3)
        tv1 = findViewById(R.id.textView2)

        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.expand_in)
        tv1.startAnimation(animation)
        tv.startAnimation(animation)
    }

    private fun requestPermissions() {
        // Check if the permission is already granted
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_GRANTED
        ) {
            // Permission is already granted, handle accordingly
            Toast.makeText(this, "Permission already granted.", Toast.LENGTH_SHORT).show()
            // You can proceed with your task here if needed
        } else {
            // Permission is not granted, request it using Dexter
            Dexter.withActivity(this)
                .withPermissions(
                    WRITE_EXTERNAL_STORAGE
                )
                .withListener(object : MultiplePermissionsListener {
                    override fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        // Handle permissions check response
                        if (report.areAllPermissionsGranted()) {
                            Toast.makeText(this@Select, "All permissions are granted.", Toast.LENGTH_SHORT).show()
                            // Proceed with your task
                        }
                        if (report.isAnyPermissionPermanentlyDenied) {
                            showSettingsDialog()
                        }
                    }

                    override fun onPermissionRationaleShouldBeShown(
                        permissions: MutableList<PermissionRequest>,
                        token: PermissionToken
                    ) {
                        token.continuePermissionRequest()
                    }
                })
                .withErrorListener { error ->
                    Toast.makeText(applicationContext, "Error occurred: $error", Toast.LENGTH_SHORT).show()
                }
                .onSameThread()
                .check()
        }
    }

    private fun showSettingsDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Need Permissions")
            .setCancelable(false)
            .setMessage("This app needs permission to use this feature. You can grant them in app settings.")
            .setPositiveButton("GOTO SETTINGS") { dialog, _ ->
                dialog.cancel()
                val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                uri = Uri.fromParts("package", packageName, null)
                intent.data = uri
                startActivityForResult(intent, 101)
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.cancel()
            }
            .show()
    }

    fun opengallery(view: View) {
        ImagePicker.with(this)
            .crop()
            .galleryOnly()
            .start()
    }

    fun opencamera(view: View) {
        ImagePicker.with(this)
            .crop()
            .cameraOnly()
            .start()
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 101 && resultCode == RESULT_OK) {
            // Handle result from app settings activity if needed
        } else if (requestCode == ImagePicker.REQUEST_CODE && resultCode == RESULT_OK) {
            uri = data?.data
            uri?.let {
                val intent = Intent(applicationContext, EditActivity::class.java)
                intent.putExtra("uri", it.toString())
                startActivity(intent)
            }
        }
    }
}