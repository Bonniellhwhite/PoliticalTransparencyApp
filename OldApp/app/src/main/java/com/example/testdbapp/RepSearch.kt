package com.example.testdbapp
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import android.widget.SearchView
import android.widget.Switch
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RepSearch : AppCompatActivity(){
    val dummyRepresentativesData = listOf(
        "Rep. Alex Smith - District 1: Environmental Committee Chair",
        "Rep. Jamie Johnson - District 2: Health and Human Services",
        "Rep. Chris Lee - District 3: Education Board Member",
        "Rep. Morgan Brown - District 4: Infrastructure and Transportation",
        "Rep. Taylor White - District 5: National Security Advisor",
        "Rep. Jordan Davis - District 6: Economic Development Committee",
        "Rep. Casey Kim - District 7: Social Welfare Program Director",
        "Rep. Pat Morgan - District 8: Immigration Reform Advocate",
        "Rep. Riley Wilson - District 9: Tax Policy Review Board",
        "Rep. Jesse Rivera - District 10: Energy and Natural Resources",
        "Rep. Sam Kelly - District 11: Climate Change Task Force",
        "Rep. Alex Bailey - District 12: Labor and Employment",
        "Rep. Jordan Casey - District 13: Gun Control Legislation",
        "Rep. Taylor Brooks - District 14: Privacy and Civil Liberties Oversight",
        "Rep. Chris Jordan - District 15: Drug Policy and Enforcement",
        "Rep. Pat Taylor - District 16: Foreign Affairs Committee",
        "Rep. Jamie Lee - District 17: Agriculture and Rural Development",
        "Rep. Morgan Smith - District 18: Transportation Safety Board",
        "Rep. Casey White - District 19: Housing and Urban Development",
        "Rep. Riley Brown - District 20: Healthcare System Improvement Task Force"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.rep_search_filter)

            val backBillSearch: ImageButton = findViewById(R.id.btn_nav_home)
            backBillSearch.setOnClickListener {
                finish()
            }


            val searchView = findViewById<SearchView>(R.id.searchView)

            // Set up the listener for the searchView
            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.let {
                        // Filter the dummy data based on the query
                        val filteredResults = dummyRepresentativesData.filter { it.contains(query, ignoreCase = true) }
                        Log.d("Search", "Filtered Results Count: ${filteredResults.size}")

                        // Set up the RecyclerView with the filtered results
                        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSearchResults2)
                        recyclerView.layoutManager = LinearLayoutManager(this@RepSearch)
                        recyclerView.adapter = SearchResultAdapter(filteredResults)


                    }

                    searchView.clearFocus()

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })

        }
    }

