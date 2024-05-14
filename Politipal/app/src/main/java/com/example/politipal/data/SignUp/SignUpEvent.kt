package com.example.politipal.data.SignUp
sealed class SignUpEvent {
    data class FirstNameChange(val firstName:String) : SignUpEvent()
    data class LastNameChange(val lastName:String) : SignUpEvent()
    data class EmailChange(val email:String) : SignUpEvent()
    data class PasswordChange(val password:String) : SignUpEvent()

    //object LoginButton : SignUpEvent()
    object SignUpButton : SignUpEvent()
}