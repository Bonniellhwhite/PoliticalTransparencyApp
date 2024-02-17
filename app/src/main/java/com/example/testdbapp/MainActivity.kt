package com.example.testdbapp

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.google.firebase.database.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.DatabaseError
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
import com.example.testdbapp.BillSearchFilter
import com.example.testdbapp.RepSearchFilter

class MainActivity : ComponentActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize Firebase Database
        database = Firebase.database
        myRef = database.getReference("message")





        val add_button: Button = findViewById(R.id.add_button)
        add_button.setOnClickListener {
            write()
            read()
        }

        val navToBillSearch: Button = findViewById(R.id.btn_bill_search)
        navToBillSearch.setOnClickListener{
            setContentView(R.layout.bill_search_filter)
        }

        val navToRepSearch: Button = findViewById(R.id.btn_rep_search)
        navToRepSearch.setOnClickListener{
            setContentView(R.layout.rep_search_filter)
        }

    }

    fun write(){
        // Write a message to the database
        myRef.setValue("Hello, World!")
    }

    fun read(){
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val value = snapshot.getValue<String>()
                Log.d("MainActivity", "Value is: $value")
                val dataString =  value

                val textView = findViewById<TextView>(R.id.dbtextView)
                textView.text = dataString
            }

            override fun onCancelled(error: DatabaseError) {
                Log.w("MainActivity", "Failed to read value.", error.toException())
            }
        })

    }
}