package com.hidayat.photoeditor

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity


class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val img = findViewById<View>(R.id.imageView)
        val tv = findViewById<View>(R.id.textView)

        val animation: Animation = AnimationUtils.loadAnimation(applicationContext, R.anim.expand_in)
        img.startAnimation(animation)
        tv.startAnimation(animation)

        Handler(Looper.getMainLooper()).postDelayed(
            {
                val intent = Intent(this, Select::class.java)
                startActivity(intent)
            }, 3000
        )
    }
}