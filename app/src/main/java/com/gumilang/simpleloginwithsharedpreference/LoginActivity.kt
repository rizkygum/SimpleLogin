package com.gumilang.simpleloginwithsharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository
    private lateinit var isUsername: String
    private lateinit var isPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val session = SessionManager(this)
        userRepository = UserRepository.getInstance(session)

        if (userRepository.isUserLogin()) {
            moveToHome()
        }

        btn_login.setOnClickListener {
            isUsername = et_username.text.toString().trim()
            isPassword = et_password.text.toString().trim()

            when {
                isUsername == "" -> {
                    et_username.error = "Please Input Username"
                    et_username.requestFocus()
                }
                isPassword == "" -> {
                    et_password.error = "Please Input Password"
                    et_password.requestFocus()
                }
                else -> {
                    saveSession(isUsername)
                }
            }
        }
    }

    private fun saveSession(username: String) {
        userRepository.loginUser(username)
        moveToHome()
    }

    private fun moveToHome() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}