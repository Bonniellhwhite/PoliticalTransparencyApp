package com.example.politipal.ui.components

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.politipal.R
import com.example.politipal.ui.theme.primaryLight


@Composable
fun NormalText(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxHeight()
            .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.scrimLight),
        textAlign = TextAlign.Center
    )
}
@Composable
fun headingSU(value:String){
    Text(
        text = value,
        modifier = Modifier
            .fillMaxHeight()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Normal
        ), color = colorResource(id = R.color.scrimLight),
        textAlign = TextAlign.Center
    )
}


@Composable
fun TextField(labelValue: String) {
    val textValue = remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
            textValue.value = it
        }
    )
}

@Composable
fun PasswordTextField(labelValue: String){
    val password = remember { mutableStateOf("")
    }

    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        label = { Text(text = labelValue) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password.value,
        onValueChange = {
            password.value = it
        }
    )
}

@Composable
fun ButtonComp(value: String){
    Button(onClick ={/*TODO*/},
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ){
        Box(modifier= Modifier
            .fillMaxWidth()
            .heightIn(48.dp)
            .background(
                color = primaryLight,
                shape = RoundedCornerShape(50.dp)
            ),
            contentAlignment = Alignment.Center
            ){
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

        }

    }

    }

@Composable
fun ClickableLogin(onTextSelected: (String) -> Unit){
    val initialText = "Already Have An Account?"
    val LoginText = " Login"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = primaryLight)){
            pushStringAnnotation(tag = LoginText, annotation = LoginText)
            append(LoginText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxHeight()
            .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        text = annotatedString, onClick = {offset ->
        annotatedString.getStringAnnotations(offset, offset)
            .firstOrNull()?.also{span ->
                Log.d("ClickableTextComponent", "{${span.item}}")

                if(span.item == LoginText){
                    onTextSelected(span.item)
                }


            }

    })

    }

@Composable
fun ClickableSignUp(onTextSelected: (String) -> Unit){
    val initialText = "Do Not Have An Account?"
    val SignUpText = " Sign Up"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = primaryLight)){
            pushStringAnnotation(tag = SignUpText, annotation = SignUpText)
            append(SignUpText)
        }
    }
    ClickableText(
        modifier = Modifier
            .fillMaxHeight()
            .heightIn(min = 10.dp),
        style = TextStyle(
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            fontStyle = FontStyle.Normal
        ),
        text = annotatedString, onClick = {offset ->
            annotatedString.getStringAnnotations(offset, offset)
                .firstOrNull()?.also{span ->
                    Log.d("ClickableTextComponent", "{${span.item}}")

                    if(span.item == SignUpText){
                        onTextSelected(span.item)
                    }


                }

        })

}