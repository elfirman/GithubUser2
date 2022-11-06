package com.elfirman.githubusersubmission

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.elfirman.githubusersubmission.databinding.ActivitySplashBinding
import com.elfirman.githubusersubmission.ui.main.MainActivity
import com.elfirman.githubusersubmission.util.Constanta.TIME_SPLASH

class SplashActivity : AppCompatActivity() {

    private lateinit var splashBinding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(splashBinding.root)

        val handler = Handler(mainLooper)

        handler.postDelayed({
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, TIME_SPLASH)


        supportActionBar?.hide()
    }
}