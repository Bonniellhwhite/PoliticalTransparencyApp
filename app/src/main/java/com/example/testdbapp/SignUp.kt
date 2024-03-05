package com.example.testdbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val home: ImageButton = findViewById(R.id.btn_return_home_bill)
        home.setOnClickListener {
            finish( )
        }

        val navBackToLogin: Button = findViewById(R.id.btn_direct_login)
        navBackToLogin.setOnClickListener{

            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }
    }
}