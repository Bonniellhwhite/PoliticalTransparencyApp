package com.example.politipal.data.Login

sealed class LoginUIEvent {

    data class EmailChanged(val email:String): LoginUIEvent()
    data class PasswordChanged(val password: String) : LoginUIEvent()

    object LoginButton : LoginUIEvent()
    //object SignUpButton : LoginUIEvent()
}