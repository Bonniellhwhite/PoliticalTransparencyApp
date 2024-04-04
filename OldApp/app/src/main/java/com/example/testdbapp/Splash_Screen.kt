package com.example.testdbapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_screen)

        // Delay for 2 seconds and then start the main activity
        val splashScreenDuration = 2000L // 2000 milliseconds = 2 seconds
        val mainActivityIntent = Intent(this, MainActivity::class.java)

        // Start MainActivity after splashScreenDuration
        // using a coroutine to avoid blocking the UI thread
        lifecycle.coroutineScope.launch {
            delay(splashScreenDuration)
            startActivity(mainActivityIntent)
            finish() // close the splash screen activity
        }
    }
}