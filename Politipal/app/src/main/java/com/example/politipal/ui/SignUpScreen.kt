package com.example.politipal.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.politipal.R
import com.example.politipal.ui.components.ButtonComp
import com.example.politipal.ui.components.ClickableLogin
import com.example.politipal.ui.components.NormalText
import com.example.politipal.ui.components.PasswordTextField
import com.example.politipal.ui.components.TextField
import com.example.politipal.ui.components.headingSU



@Composable
fun SignUpScreen() {
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
                text = "Hey there,",
                modifier = Modifier.padding(15.dp),
                    style = TextStyle(
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
            )
            Text(
                text = "Create An Account!",
                modifier = Modifier.padding(15.dp),
                style = TextStyle(
                    fontSize = 36.sp, // Increase the font size
                    fontWeight = FontWeight.Bold, // Make it bold
                    textAlign = TextAlign.Center
                )
            )
            Spacer(modifier = Modifier.height(60.dp))
            TextField(labelValue = stringResource(id = R.string.first_name))
            Spacer(modifier = Modifier.height(10.dp))
            TextField(labelValue = stringResource(id = R.string.last_name))
            Spacer(modifier = Modifier.height(10.dp))
            TextField(labelValue = stringResource(id = R.string.email))
            Spacer(modifier = Modifier.height(10.dp))
            PasswordTextField(labelValue = stringResource(id = R.string.password))
            Spacer(modifier = Modifier.height(40.dp))
            ButtonComp(value = stringResource(id = R.string.sign_up))
            Spacer(modifier = Modifier.height(20.dp))
            ClickableLogin(onTextSelected = {
            })
        }
    }

}


@Preview
@Composable
fun DefaultPreviewOfSignUpScreen(){
    SignUpScreen()

}