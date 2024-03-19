package com.example.testdbapp
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class BillSearch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_search_filter)

        val backBillSearch: ImageButton = findViewById(R.id.btn_nav_home)
        backBillSearch.setOnClickListener {
            finish()
        }





    }
}