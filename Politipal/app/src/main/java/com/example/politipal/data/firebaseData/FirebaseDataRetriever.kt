package com.example.politipal.data.firebaseData

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.politipal.data.Rep
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await


class FirebaseDataRetriever {

    // All reps
    var fullReplist = mutableListOf<Rep>()


    fun getRepsList(): List<Rep>{
        Log.d(TAG,fullReplist.size.toString())
        return fullReplist;
    }

    fun fetchFirebaseReps(): Flow<List<Rep>> = flow{
        try {
            val db = Firebase.firestore
            val result = db.collection("reps").get().await()
            val reps = result.documents.mapNotNull { document ->
                document.toObject(Rep::class.java)
            }
            fullReplist.addAll(reps)
            emit(reps)  // Emit the fetched emails
        }catch (exception: Exception) {
            // Handle any errors, possibly emitting an error state or logging
            Log.e("FirestoreError", "Error fetching emails from Firestore", exception)
        }
    }
}