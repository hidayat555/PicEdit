package com.hidayat.photoeditor

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dsphotoeditor.sdk.activity.DsPhotoEditorActivity
import com.dsphotoeditor.sdk.utils.DsPhotoEditorConstants


class EditActivity : AppCompatActivity() {
    private var uri: String? = null
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit)

        uri = intent.getStringExtra("uri")
        imageView = findViewById(R.id.img)

        val animation = AnimationUtils.loadAnimation(applicationContext, R.anim.expand_in)
        imageView.startAnimation(animation)

        val dsPhotoEditorIntent = Intent(this, DsPhotoEditorActivity::class.java)
        dsPhotoEditorIntent.data = Uri.parse(uri)
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_OUTPUT_DIRECTORY, "Photo Editor")
        val toolsToHide = intArrayOf(DsPhotoEditorActivity.TOOL_CONTRAST, DsPhotoEditorActivity.TOOL_SATURATION,
                                     DsPhotoEditorActivity.TOOL_ROUND, DsPhotoEditorActivity.TOOL_PIXELATE, DsPhotoEditorActivity.TOOL_SHARPNESS,
                                     DsPhotoEditorActivity.TOOL_EXPOSURE, DsPhotoEditorActivity.TOOL_FRAME, DsPhotoEditorActivity.TOOL_VIGNETTE,
                                     DsPhotoEditorActivity.TOOL_WARMTH, DsPhotoEditorActivity.TOOL_ORIENTATION, DsPhotoEditorActivity.TOOL_FILTER
            )
        dsPhotoEditorIntent.putExtra(DsPhotoEditorConstants.DS_PHOTO_EDITOR_TOOLS_TO_HIDE, toolsToHide)
        Handler(Looper.getMainLooper()).post{
            startActivityForResult(dsPhotoEditorIntent, 200)
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            when (requestCode) {
                200 -> {
                    var outputUri: Uri? = data?.data
                    imageView.setImageURI(outputUri)
                }
            }
        }
    }
}