package com.example.testdbapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database

class MainActivity : ComponentActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Initialize Firebase Database
        database = Firebase.database
        myRef = database.getReference("message")

        // Navigation buttons
        val navToBillSearch: ImageButton = findViewById(R.id.btn_nav_bills)
        navToBillSearch.setOnClickListener{
            val intent = Intent(this, BillSearch::class.java)
            startActivity(intent)
        }

        // Navigation Rep Button
        val navToRepSearch: ImageButton = findViewById(R.id.btn_nav_reps)
        navToRepSearch.setOnClickListener{
            val intent = Intent(this, RepSearch::class.java)
            startActivity(intent)
        }

        /*   // disabling for now, code For future - Bonnie 3/20
        val navToLogin: Button = findViewById(R.id.btn_login)
        navToLogin.setOnClickListener{
            val intent = Intent(this, SignIn::class.java)
            startActivity(intent)
        }

        val navToSignUp: Button = findViewById(R.id.btn_sign_up)
        navToSignUp.setOnClickListener{
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)

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
    }*/
    }
}
