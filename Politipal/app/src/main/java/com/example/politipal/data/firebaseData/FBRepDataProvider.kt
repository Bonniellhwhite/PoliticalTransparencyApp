
package com.example.politipal.data.firebaseData

import com.example.politipal.R
import com.example.politipal.data.Rep
object FBRepDataProvider {

    // Static test entries
    val staticTestRepData = listOf(
        Rep(
            id = "ID1",
            channels = "String",
            division = "String",
            emails= "String",
            name= "Rep 1",
            party= "String",
            phones= "String",
            roles= "String"
        ),
        Rep(
            id = "ID2",
            channels = "String",
            division = "String",
            emails= "String",
            name= "Rep 2",
            party= "String",
            phones= "String",
            roles= "String"
        ),
        Rep(
            id = "ID3",
            channels = "String",
            division = "String",
            emails= "String",
            name= "Rep 3",
            party= "String",
            phones= "String",
            roles= "String"
        )
    )


    // Actual DB Pulling Functions
    //TODO

    // List Interaction Functions
    /**
     * Get the current user's default account.
     */
    fun getDefaultUserAccount() = staticTestRepData.first()

    /**
     * Whether or not the given [Account.id] uid is an account owned by the current user.
     */
    fun isUserAccount(id: String): Boolean = staticTestRepData.any { it.id == id }

    /**
     * Get the contact of the current user with the given [accountId].
     */
    fun getContactAccountByUid(id: String): Rep {
        return staticTestRepData.first { it.id == id }
    }
}
