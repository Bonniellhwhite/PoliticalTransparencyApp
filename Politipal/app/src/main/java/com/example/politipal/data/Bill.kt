
package com.example.politipal.data

/**
 * A simple data class to represent a Bill.
 */

data class Bill(
    val id: Long,
    val number: Int,
    val originChamber: String,
    val originChamberCode: String,
    val title: String,
    val type: String,
    val updateDate: String,
    val updateDateIncludingText: String,
    val url: String,
    var isStarred: Boolean = false
)
