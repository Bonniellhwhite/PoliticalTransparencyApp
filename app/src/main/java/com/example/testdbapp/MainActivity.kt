package com.example.testdbapp

import android.database.sqlite.SQLiteOpenHelper
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.testdbapp.ui.theme.TestDBappTheme
import android.util.Log

import com.example.testdbapp.MyDatabaseHelper
import android.widget.Button

class MainActivity : ComponentActivity() {


    private lateinit var dbHelper: MyDatabaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TestDBappTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {



                    setContentView(R.layout.activity_main)

                    dbHelper = MyDatabaseHelper(this)

                    val add_button: Button = findViewById(R.id.add_button)
                    add_button.setOnClickListener {
                        add_db()
                    }


                }
            }
        }
    }

    fun add_db() {
        // Insert data

        val newRowId = dbHelper.insertData("John Doe")
        Log.d("MyApp", "DataSuccess $newRowId")

    }

    fun show_db(){

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TestDBappTheme {
        Greeting("Android")
    }
}