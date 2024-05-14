package com.example.politipal.data

import DCodeIcon

data class ProfilePopularList(
    val name: String,
    val description: String,
    val star: String,
    val language: String
)

data class ImageTextList(
    val icon: DCodeIcon,
    val text: String
)