
package com.example.politipal.data.firebaseData

import com.example.politipal.R
import com.example.politipal.data.Account

/**
 * An static data store of [Account]s. This includes both [Account]s owned by the current user and
 * all [Account]s of the current user's contacts.
 */
object LocalAccountsDataProvider {

    val allUserAccounts = listOf(
        Account(
            id = 1L,
            uid = 0L,
            firstName = "Jeff",
            lastName = "Hansen",
            email = "hikingfan@gmail.com",
            altEmail = "hkngfan@outside.com",
            avatar = R.drawable.avatar_express,
            isCurrentAccount = true
        ),
        Account(
            id = 2L,
            uid = 0L,
            firstName = "Jeff",
            lastName = "H",
            email = "jeffersonloveshiking@gmail.com",
            altEmail = "jeffersonloveshiking@work.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 3L,
            uid = 0L,
            firstName = "Jeff",
            lastName = "Hansen",
            email = "jeffersonc@google.com",
            altEmail = "jeffersonc@gmail.com",
            avatar = R.drawable.avatar_express
        )
    )

    private val allUserContactAccounts = listOf(
        Account(
            id = 4L,
            uid = 1L,
            firstName = "Tracy",
            lastName = "Alvarez",
            email = "tracealvie@gmail.com",
            altEmail = "tracealvie@gravity.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 5L,
            uid = 2L,
            firstName = "Allison",
            lastName = "Trabucco",
            email = "atrabucco222@gmail.com",
            altEmail = "atrabucco222@work.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 6L,
            uid = 3L,
            firstName = "Ali",
            lastName = "Connors",
            email = "aliconnors@gmail.com",
            altEmail = "aliconnors@android.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 7L,
            uid = 4L,
            firstName = "Alberto",
            lastName = "Williams",
            email = "albertowilliams124@gmail.com",
            altEmail = "albertowilliams124@chromeos.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 8L,
            uid = 5L,
            firstName = "Kim",
            lastName = "Alen",
            email = "alen13@gmail.com",
            altEmail = "alen13@mountainview.gov",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 9L,
            uid = 6L,
            firstName = "Google",
            lastName = "Express",
            email = "express@google.com",
            altEmail = "express@gmail.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 10L,
            uid = 7L,
            firstName = "Sandra",
            lastName = "Adams",
            email = "sandraadams@gmail.com",
            altEmail = "sandraadams@textera.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 11L,
            uid = 8L,
            firstName = "Trevor",
            lastName = "Hansen",
            email = "trevorhandsen@gmail.com",
            altEmail = "trevorhandsen@express.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 12L,
            uid = 9L,
            firstName = "Sean",
            lastName = "Holt",
            email = "sholt@gmail.com",
            altEmail = "sholt@art.com",
            avatar = R.drawable.avatar_express
        ),
        Account(
            id = 13L,
            uid = 10L,
            firstName = "Frank",
            lastName = "Hawkins",
            email = "fhawkank@gmail.com",
            altEmail = "fhawkank@thisisme.com",
            avatar = R.drawable.avatar_express
        )
    )

    /**
     * Get the current user's default account.
     */
    fun getDefaultUserAccount() = allUserAccounts.first()

    /**
     * Whether or not the given [Account.id] uid is an account owned by the current user.
     */
    fun isUserAccount(uid: Long): Boolean = allUserAccounts.any { it.uid == uid }

    /**
     * Get the contact of the current user with the given [accountId].
     */
    fun getContactAccountByUid(accountId: Long): Account {
        return allUserContactAccounts.first { it.id == accountId }
    }
}
