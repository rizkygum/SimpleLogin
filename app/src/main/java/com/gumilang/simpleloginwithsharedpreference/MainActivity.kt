package com.gumilang.simpleloginwithsharedpreference

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val session = SessionManager(this)
        userRepository = UserRepository.getInstance(session)

        tv_username.text = userRepository.getUser()

        btn_logout.setOnClickListener {
            userRepository.userLogout()
            moveToMainActivity()
        }
    }

    private fun moveToMainActivity() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }
}