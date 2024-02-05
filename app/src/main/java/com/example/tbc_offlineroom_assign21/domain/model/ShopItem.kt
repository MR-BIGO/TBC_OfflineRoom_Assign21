package com.example.tbc_offlineroom_assign21.domain.model

data class ShopItem(
    val id: Int,
    val img: String,
    val price: String,
    val title: String,
    val isFavourite: Boolean,
    val category: String
)
