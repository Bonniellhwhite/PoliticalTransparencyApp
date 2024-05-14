package com.example.politipal.data;

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableStateFlow

data class Rep(
        val id: String,
        val address: String,
        val ballotpediaID: String,
        val bioguideID: String?,
        val birthday: Int,
        val cspanID: Int,
        val district: String,
        val facebook: String,
        val fecIDS: String,
        val firstName: String,
        val fullName: String,
        val gender: String,
        val govtrackID: Int,
        val icpsrID: Int,
        val middleName: String,
        val opensecretsID: String,
        val party: String,
        val phone: Int,
        val rssURL: String,
        val state: String,
        val surname: String,
        val thomasID: String,
        val twitter: String,
        val twitterID: Long,
        val type: String,
        val url: String,
        val votesmartID: Int,
        val wikipediaID: String,
        val youtube: String,
        val youtubeID: String
    ){
        fun contains(query: String, filters: MutableState<Set<RepFilterOptions>>):Boolean {
                // Decides if a single Rep
                val matchingCombinations = listOf(
                        "$firstName $fullName",
                        "${firstName.first()} ${fullName.first()}",
                )
                val matchesName = matchingCombinations.any { it.contains(query, ignoreCase = true) }

                return if(filters.value.isNotEmpty()){
                        val matchesFilters = filters.value.all { it.matches(this) }
                        matchesName && matchesFilters
                }else{
                        matchesName
                }

        }
}

enum class RepFilterOptions {
        MALE, FEMALE, DEMOCRAT, REPUBLICAN, SENATE, HOUSE;
        fun matches(rep: Rep): Boolean {
                return when (this) {
                        MALE -> rep.gender == "M"
                        FEMALE -> rep.gender == "F"
                        DEMOCRAT -> rep.party == "Democrat"
                        REPUBLICAN -> rep.party == "Republican"
                        SENATE -> "senate" in rep.url.lowercase()
                        HOUSE -> "house" in rep.url.lowercase()
                        //OTHER -> rep.party != "Democrat" || rep.party != "Republican"
                }
        }
}

