
package com.example.politipal.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * An object which represents an account which can belong to a user. A single user can have
 * multiple accounts.
 */
data class Account(
    val id: Long,
    val uid: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val altEmail: String,
    val notify: Boolean,
    @DrawableRes val avatar: Int,
    var isCurrentAccount: Boolean = false
) {
    val fullName: String = "$firstName $lastName"
}
