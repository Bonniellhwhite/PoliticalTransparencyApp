package com.example.testdbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)

        val home: ImageButton = findViewById(R.id.btn_return_home_bill)
        home.setOnClickListener {
            finish()
        }

        val navBackToSignUp: Button = findViewById(R.id.btn_direct_sign_up)
        navBackToSignUp.setOnClickListener{

            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }
    }
}