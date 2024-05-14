

package com.example.politipal.ui.Login

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.example.politipal.ui.navigation.PoliAppRouter
import com.example.politipal.ui.navigation.Screen
import com.example.politipal.data.HomeViewModel

@Composable
fun PolitipalApp(homeViewModel: HomeViewModel = viewModel()) {
    val navController = rememberNavController()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {

        if (homeViewModel.isUserLoggedIn.value == true) {
            PoliAppRouter.navigateTo(Screen.HomeScreen)
        }

        Crossfade(targetState = PoliAppRouter.currentScreen) { currentState ->
            when (currentState.value) {
                is Screen.SignUpScreen -> {
                    SignUpScreen(navController = navController)
                }

                is Screen.LoginScreen -> {
                    LoginScreen(navController = navController)
                }

                is Screen.HomeScreen -> {/*TODO*/
                }
            }
        }

    }
}