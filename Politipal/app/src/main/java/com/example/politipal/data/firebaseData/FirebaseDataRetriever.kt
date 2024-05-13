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
            val result = db.collection("allLegislators").get().await()
            Log.d(ContentValues.TAG,"Recieved Rep Data")
            for (document in result.documents) {
                val id = document.id.toString()
                val address = document.getString("address") ?: ""
                val ballotpediaID = document.getString("ballotpediaID") ?: ""
                val bioguideID = document.getString("bioguideID")
                val birthday = document.getLong("birthday")?.toInt() ?: 0
                val cspanID = document.getLong("cspanID")?.toInt() ?: 0
                val district = document.getString("district") ?: ""
                val facebook = document.getString("facebook") ?: ""
                val fecIDS = document.getString("fecIDS") ?: ""
                val firstName = document.getString("firstName") ?: ""
                val fullName = document.getString("fullName") ?: ""
                val gender = document.getString("gender") ?: ""
                val govtrackID = document.getLong("govtrackID")?.toInt() ?: 0
                val icpsrID = document.getLong("icpsrID")?.toInt() ?: 0
                val middleName = document.getString("middleName") ?: ""
                val opensecretsID = document.getString("opensecretsID") ?: ""
                val party = document.getString("party") ?: ""
                val phone = document.getLong("phone")?.toInt() ?: 0
                val rssURL = document.getString("rssURL") ?: ""
                val state = document.getString("state") ?: ""
                val surname = document.getString("surname") ?: ""
                val thomasID = document.getString("thomasID") ?: ""
                val twitter = document.getString("twitter") ?: ""
                val twitterID = document.getLong("twitterID") ?: 0
                val type = document.getString("type") ?: ""
                val url = document.getString("url") ?: ""
                val votesmartID = document.getLong("votesmartID")?.toInt() ?: 0
                val wikipediaID = document.getString("wikipediaID") ?: ""
                val youtube = document.getString("youtube") ?: ""
                val youtubeID = document.getString("youtubeID") ?: ""


                // Create Rep object
                val rep = Rep(
                    id, address, ballotpediaID, bioguideID, birthday, cspanID, district, facebook,
                    fecIDS, firstName, fullName, gender, govtrackID, icpsrID, middleName, opensecretsID,
                    party, phone, rssURL, state, surname, thomasID, twitter, twitterID, type, url,
                    votesmartID, wikipediaID, youtube, youtubeID
                )
                fullReplist.add(rep)}
                emit(fullReplist)  // Emit the fetched emails
        }catch (exception: Exception) {
            // Handle any errors, possibly emitting an error state or logging
            Log.e("FirestoreError", "Error fetching emails from Firestore", exception)
        }
    }
}