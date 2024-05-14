package com.example.politipal.ui.Login

import LoginView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.politipal.R
import com.example.politipal.data.Login.LoginUIEvent
import com.example.politipal.ui.components.ButtonComp
import com.example.politipal.ui.components.ClickableLogin
import com.example.politipal.ui.components.PasswordTextField
import com.example.politipal.ui.components.TextField

@Composable
fun LoginScreen(navController: NavController, loginView: LoginView = viewModel()) {
    Surface(
        color = Color.White,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(28.dp)

    ){
        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Top) {
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Welcome back,",
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )
            )
            Text(
                text = "Login!",
                modifier = Modifier.padding(20.dp),
                style = TextStyle(
                    fontSize = 36.sp, // Increase the font size
                    fontWeight = FontWeight.Bold, // Make it bold
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            TextField(labelValue = stringResource(id = R.string.email), onTextSelected = {
                loginView.onEvent(LoginUIEvent.EmailChanged(it))
            },
                errorStatus = loginView.loginUIState.value.emailError
            )
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(labelValue = stringResource(id = R.string.password), onTextSelected = {
                loginView.onEvent(LoginUIEvent.PasswordChanged(it))
            },
                errorStatus = loginView.loginUIState.value.passwordError

            )
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComp(value = stringResource(id = R.string.login),
                onButtonClick = {
                    loginView.onEvent(LoginUIEvent.LoginButton)
                })
            Spacer(modifier = Modifier.height(20.dp))
            ClickableLogin(tryingToLogin = false, onTextSelected = {
                navController.navigate("SignUpScreen")
            })
        }
    }

}
