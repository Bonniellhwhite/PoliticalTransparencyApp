package com.example.testdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class SignUp : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sign_up)

        val home: ImageButton = findViewById(R.id.btn_return_home_bill)
        home.setOnClickListener {
            finish()
        }
    }
}