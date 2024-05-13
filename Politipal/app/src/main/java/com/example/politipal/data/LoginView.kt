package com.example.politipal.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.google.firebase.events.Event

class LoginView: ViewModel(){
    private val TAG = LoginView::class.simpleName

    var signUpUIState = mutableStateOf(SignUpUIState())

    fun onEvent(event: UIEvent){
        when(event){
            is UIEvent.FirstNameChange ->{
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }
            is UIEvent.LastNameChange ->{
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
                printState()

            }
            is UIEvent.EmailChange ->{
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
                printState()

            }
            is UIEvent.PasswordChange ->{
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
            )
                printState()

            }
            is UIEvent.LoginButton ->{
                signUp()
            }
        }


    }
    private fun signUp(){
        Log.d(TAG, "Inside_signup")
    }
    private fun printState(){
        Log.d(TAG, "Inisde_printSate")
        Log.d(TAG, signUpUIState.value.toString())

    }
}
