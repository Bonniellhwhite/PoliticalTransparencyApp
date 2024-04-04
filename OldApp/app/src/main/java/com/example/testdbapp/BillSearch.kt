package com.example.testdbapp
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageButton
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.SearchView
import android.widget.Switch


class BillSearch : AppCompatActivity() {

    val dummyData = listOf(
        "Bill 1: Environment",
        "Bill 2: Health Care",
        "Bill 3: Education",
        "Bill 4: Infrastructure",
        "Bill 5: National Security",
        "Bill 6: Economic Policy",
        "Bill 7: Social Welfare",
        "Bill 8: Immigration",
        "Bill 9: Tax Reform",
        "Bill 10: Energy Policy",
        "Bill 11: Climate Change",
        "Bill 12: Labor Laws",
        "Bill 13: Gun Control",
        "Bill 14: Privacy Rights",
        "Bill 15: Drug Policy",
        "Bill 16: Foreign Affairs",
        "Bill 17: Agriculture",
        "Bill 18: Transportation",
        "Bill 19: Housing",
        "Bill 20: Healthcare Improvement"
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.bill_search_filter)

        val backBillSearch: ImageButton = findViewById(R.id.btn_nav_home)
        backBillSearch.setOnClickListener {
            finish()
        }

        val mySwitch: Switch = findViewById(R.id.switch2)
        val searchView = findViewById<SearchView>(R.id.searchView)

        // Set up the listener for the searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    // Filter the dummy data based on the query
                    val filteredResults = dummyData.filter { it.contains(query, ignoreCase = true) }
                    Log.d("Search", "Filtered Results Count: ${filteredResults.size}")

                    // Set up the RecyclerView with the filtered results
                    val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewSearchResults2)
                    recyclerView.layoutManager = LinearLayoutManager(this@BillSearch)
                    recyclerView.adapter = SearchResultAdapter(filteredResults)


                    mySwitch.visibility = View.VISIBLE

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