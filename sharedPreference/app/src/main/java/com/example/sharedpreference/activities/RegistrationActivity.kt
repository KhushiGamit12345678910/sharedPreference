package com.example.sharedpreference.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sharedpreference.R
import com.example.sharedpreference.const.*
import com.example.sharedpreference.databinding.ActivityRegistrationBinding


class RegistrationActivity : AppCompatActivity() {


    private lateinit var binding: ActivityRegistrationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
        val myEdit = sharedPreferences.edit()

        binding.apply {
            submitBtn.setOnClickListener {
                val fullname = registerFullname.editText?.text.toString().trim()
                val email = registerEmail.editText?.text.toString().trim()
                val password = registerPassword.editText?.text.toString().trim()
                val confirmPassword = registerConfirmPassword.editText?.text.toString().trim()

                registerFullname.error = null
                registerEmail.error = null
                registerPassword.error = null

                // -----------------> Validations of Registration form<--------------------

                if (fullname.isEmpty()) {
                    registerFullname.error = getString(R.string.enter_fullname)
                } else if (email.isEmpty()) {
                    registerEmail.error = getString(R.string.enter_email)
                } else if (!email.matches(emailPattern.toRegex())) {
                    registerEmail.error = getString(R.string.invalid_email)
                }else if (password.isEmpty()) {
                    registerPassword.error = getString(R.string.enter_password)
                } else if (confirmPassword.isEmpty()) {
                    registerConfirmPassword.error = getString(R.string.error_message)
                } else if (password != confirmPassword) {
                    registerConfirmPassword.error = getString(R.string.error)
                } else {
                    myEdit.apply() {
                        putString(EMAIL_LOGIN, email)
                        putString(PASSWORD_LOGIN, password)
                        putString(FULLNAME, fullname)
                        putBoolean(USER_LOGIN, true)
                        apply()
                    }
                    Toast.makeText(
                        this@RegistrationActivity,
                        R.string.registration_successfully,
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this@RegistrationActivity, HomeActivity::class.java)
                    startActivity(intent)
                    finishAffinity()
                }
            }
        }

    }
}

