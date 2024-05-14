package com.example.politipal.data;

import androidx.compose.runtime.MutableState
import kotlinx.coroutines.flow.MutableStateFlow

data class Bill(
        val number: Int,
        val originChamber: String,
        val originChamberCode: String,
        val title: String,
        val type: String,
        val updateDate: String,
        val updateDateIncludingText: String,
        val url: String
) {
        fun contains(query: String, filters: MutableState<Set<BillFilterOptions>>): Boolean {
                // Check if the title contains the query
                val matchesName = title.contains(query, ignoreCase = true)

                // Apply filters if any
                return if (filters.value.isNotEmpty()) {
                        val matchesFilters = filters.value.all { it.matches(this) }
                        matchesName && matchesFilters
                } else {
                        matchesName
                }
        }
}


enum class BillFilterOptions {
        SENATE, HOUSE;

        fun matches(bill: Bill): Boolean {
                return when (this) {
                        SENATE -> bill.originChamber.lowercase() == "senate"
                        HOUSE -> bill.originChamber.lowercase() == "house"
                }
        }
}

