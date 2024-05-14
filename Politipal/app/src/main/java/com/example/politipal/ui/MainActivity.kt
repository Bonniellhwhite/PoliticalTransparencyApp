package com.example.politipal.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.politipal.ui.Login.LoginScreen
import com.example.politipal.ui.Login.SignUpScreen
import com.example.politipal.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PolitipalApp()
        }
    }
}

@Composable
fun PolitipalApp() {
    val navController = rememberNavController()
    AppTheme {
        NavHost(navController = navController, startDestination = "LoginScreen") {
            composable("LoginScreen") {
                LoginScreen(navController = navController)
            }
            composable("SignUpScreen") {
                SignUpScreen(navController = navController)
            }
            // Add more destinations here if needed
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PolitipalApp()
}
