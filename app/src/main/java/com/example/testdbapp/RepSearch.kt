package com.example.testdbapp
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton

class RepSearch : AppCompatActivity(){
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.rep_search_filter)

            val backBillSearch: ImageButton = findViewById(R.id.btn_return_home_rep)
                backBillSearch.setOnClickListener {
                    finish()
            }
        }
    }

