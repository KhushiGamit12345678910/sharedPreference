package com.example.sharedpreference.activities

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.sharedpreference.R
import com.example.sharedpreference.const.*
import com.example.sharedpreference.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var editor :SharedPreferences.Editor
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
        editor = sharedPreferences.edit()

        // ----------------> get string from registration form <-------------------
        binding.textFullNameHome.text = sharedPreferences.getString(FULLNAME, "")
        binding.textEmailHome.text = sharedPreferences.getString(EMAIL_LOGIN, "")
        binding.textPasswordLogin.text = sharedPreferences.getString(PASSWORD_LOGIN, "")



        /* --------------------get string from login form ---------------------------*/

        binding.textEmailLogin.text = sharedPreferences.getString(EMAIL_LOGIN,"")

    }


    /*-------------------- Menu Option <------------------------*/

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.item_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logoutMenu -> {
                editor.putBoolean(USER_LOGIN,false)
                editor.apply()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}