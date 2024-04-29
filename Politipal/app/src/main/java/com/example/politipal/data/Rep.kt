
package com.example.politipal.data

import java.nio.channels.Channels

/**
 * A simple data class to represent a Rep.
 */

data class Reps(
    val id: String,
    val channels: String,  // Will probably need parsing for socal icons display
    val division: String,
    val emails: String,
    val name: String,
    val party: String,
    val phones: String,
    val roles: String
)
