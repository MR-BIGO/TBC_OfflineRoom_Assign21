package com.example.tbc_offlineroom_assign21.data.remote.model

import com.squareup.moshi.Json

data class ShopItemDto(
    val id: Int,
    @Json(name = "cover")
    val img: String,
    val price: String,
    val title: String,
    @Json(name = "favorite")
    val isFavourite: Boolean
)
