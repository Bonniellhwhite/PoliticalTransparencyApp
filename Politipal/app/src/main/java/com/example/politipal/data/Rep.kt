package com.example.politipal.data;

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
        fun contains(query: String):Boolean {
                val matchingCombinations = listOf(
                        "$firstName $fullName",
                        "${firstName.first()} ${fullName.first()}",
                )
                return matchingCombinations.any{
                        it.contains(query,ignoreCase = true)
                };
        }
}


