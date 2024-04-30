
package com.example.politipal.data.firebaseData

import android.content.ContentValues.TAG
import android.util.Log
import com.example.politipal.R
import com.example.politipal.data.Rep
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

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
        ),
        Rep(
            id = "ID4",
            channels = "String",
            division = "String",
            emails= "String",
            name= "Rep 4",
            party= "String",
            phones= "String",
            roles= "String"
        ),
        Rep(
            id = "ID5",
            channels = "String",
            division = "String",
            emails= "String",
            name= "Rep 5",
            party= "String",
            phones= "String",
            roles= "String"
        ),
        Rep(
            id = "ID6",
            channels = "String",
            division = "String",
            emails= "String",
            name= "Rep 6",
            party= "String",
            phones= "String",
            roles= "String"
        ),
        Rep(
            id = "ID7",
            channels = "String",
            division = "String",
            emails= "String",
            name= "Rep 7",
            party= "String",
            phones= "String",
            roles= "String"
        )
    )


    // Actual DB Pulling Functions
    // Helpful Tutorial: https://firebase.google.com/codelabs/build-android-app-with-firebase-compose#1
    //TODO
    fun getFirebaseReps(fbAPP: FirebaseApp){

    }

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
