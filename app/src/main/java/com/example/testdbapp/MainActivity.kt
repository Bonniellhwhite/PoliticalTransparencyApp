package com.example.testdbapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.google.firebase.database.ktx.database
// Added for notify with compose
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

class MainActivity : ComponentActivity() {

    private lateinit var database: FirebaseDatabase
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Add a button to trigger notify 
        // Needs to be merged (Just added without theme)
        Box(modifier = Modifier.fillMaxSize()){
            Button(onClick = {
                service.showNotification(Counter.value)
            }) { 
                Text(text="Show Notification")
            }
        }

        // Initialize Firebase Database
        database = Firebase.database
        myRef = database.getReference("message")


        // Navigation Bill Button
        val navToBillSearch: ImageButton = findViewById(R.id.btn_nav_bills)
        // Write to database and read data
        write()
        read()

        // Navigation buttons
        val navToBillSearch: Button = findViewById(R.id.btn_bill_search)
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

}
