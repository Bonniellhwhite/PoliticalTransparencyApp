package com.example.politipal.data.SignUp

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.politipal.data.UserData.SharedPreferencesHelper
import com.example.politipal.data.rules.Validator

class SignUpView() : ViewModel() {
    private val TAG = SignUpView::class.simpleName

    var signUpUIState = mutableStateOf(SignUpUIState())
    var allValidPassed = mutableStateOf(false)

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.FirstNameChange -> {
                signUpUIState.value = signUpUIState.value.copy(
                    firstName = event.firstName
                )
                printState()
            }
            is SignUpEvent.LastNameChange -> {
                signUpUIState.value = signUpUIState.value.copy(
                    lastName = event.lastName
                )
                printState()
            }
            is SignUpEvent.EmailChange -> {
                signUpUIState.value = signUpUIState.value.copy(
                    email = event.email
                )
                printState()
            }
            is SignUpEvent.PasswordChange -> {
                signUpUIState.value = signUpUIState.value.copy(
                    password = event.password
                )
                printState()
            }
            is SignUpEvent.SignUpButton -> {
                //TODO nnav to home
            }
            else -> {
            }
        }
        validateDataWithRules()
    }


    private fun validateDataWithRules() {
        val fNameResult = Validator.validateFirstName(
            fName = signUpUIState.value.firstName
        )
        val lNameResult = Validator.validateLastName(
            lName = signUpUIState.value.lastName
        )
        val emailResult = Validator.validateEmail(
            email = signUpUIState.value.email
        )
        val passwordResult = Validator.validatePassword(
            password = signUpUIState.value.password
        )

        Log.d(TAG, "Inside_validateDataWithRules")
        Log.d(TAG, "fNameResult = $fNameResult")
        Log.d(TAG, "lNameResult = $lNameResult")
        Log.d(TAG, "emailResult = $emailResult")
        Log.d(TAG, "passwordResult = $passwordResult")

        signUpUIState.value = signUpUIState.value.copy(
            firstNameError = fNameResult.status,
            lastNameError = lNameResult.status,
            emailNameError = emailResult.status,
            passwordError = passwordResult.status
        )
        allValidPassed.value = fNameResult.status && lNameResult.status && emailResult.status && passwordResult.status
    }
    private fun printState() {
        Log.d(TAG, "Inside_printState")
        Log.d(TAG, signUpUIState.value.toString())
    }
}
