package com.example.politipal.data.UserData

import android.content.Context
import android.content.SharedPreferences

object SharedPreferencesHelper {
    private const val PREFS_NAME = "UserData"
    private const val PREF_USERNAME = "username"
    private const val PREF_PASSWORD = "password"
    private const val DEFAULT_STRING_VALUE = ""

    fun saveUserData(context: Context, username: String, password: String) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        with(sharedPreferences.edit()) {
            putString(PREF_USERNAME, username)
            putString(PREF_PASSWORD, password)
            apply()
        }
    }

    fun getUserData(context: Context): Pair<String, String> {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString(PREF_USERNAME, DEFAULT_STRING_VALUE) ?: DEFAULT_STRING_VALUE
        val password = sharedPreferences.getString(PREF_PASSWORD, DEFAULT_STRING_VALUE) ?: DEFAULT_STRING_VALUE
        return Pair(username, password)
    }
}
