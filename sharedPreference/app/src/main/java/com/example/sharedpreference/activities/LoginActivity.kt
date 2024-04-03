package com.example.sharedpreference.activities

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.sharedpreference.R
import com.example.sharedpreference.const.*
import com.example.sharedpreference.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.textRegistration.setOnClickListener {
            startActivity(Intent(this, RegistrationActivity::class.java))
        }
        validation()
    }


    private fun validation() {
        val sharedPreferences = getSharedPreferences("myPref", MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        binding.apply {
            loginBtn.setOnClickListener {
                val email = loginEmail.editText?.text.toString().trim()
                val password = loginPassword.editText?.text.toString().trim()

                val getEmail = textOne.text.toString()
                val getPassword = textTwo.text.toString()

                val emailOne = sharedPreferences.getString(EMAIL_LOGIN,getEmail)
                val pass= sharedPreferences.getString(PASSWORD_LOGIN,getPassword)

                loginEmail.error=null
                loginPassword.error=null


                 // ---------------> validations of Login Screen <--------------------
                if (email.isEmpty()) {
                    loginEmail.error = getString(R.string.invalid_email)
                } else if (!email.matches(emailPattern.toRegex())) {
                    loginEmail.error = getString(R.string.invalid_email)
                }else if (password.isEmpty()){
                    loginPassword.error = getString(R.string.enter_password)
                } else if (email !=emailOne || password!=pass) {
                    Toast.makeText(this@LoginActivity, R.string.invalid_login_pass, Toast.LENGTH_SHORT).show()
                } else {
                    val builder = AlertDialog.Builder(this@LoginActivity)
                    builder.setTitle(R.string.set_title)
                    builder.setMessage(R.string.dialog_message)


                    Toast.makeText(this@LoginActivity, R.string.login_successfully, Toast.LENGTH_SHORT).show()
                    val i = Intent(this@LoginActivity, HomeActivity::class.java)
                    i.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(i)
                    finish( )

                }

            }

        }
    }
}




