package com.example.testdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class HomePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val home: ImageButton = findViewById(R.id.btn_return_home_home_page)
        home.setOnClickListener {
            finish()
        }
    }

}