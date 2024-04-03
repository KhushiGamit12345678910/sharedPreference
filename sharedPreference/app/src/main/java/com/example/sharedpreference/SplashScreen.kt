package com.example.sharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import com.example.sharedpreference.activities.HomeActivity
import com.example.sharedpreference.activities.LoginActivity
import com.example.sharedpreference.const.USER_LOGIN

class SplashScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        Handler(Looper.getMainLooper()).postDelayed({
            if (sharedPreferences.getBoolean(USER_LOGIN, false)) {
                startActivity(Intent(this@SplashScreen, HomeActivity::class.java))
            } else {
                startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
            }
            finish()
        }, 3000)
    }
}