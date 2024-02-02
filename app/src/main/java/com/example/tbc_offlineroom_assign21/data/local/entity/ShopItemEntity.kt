package com.example.tbc_offlineroom_assign21.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "items")
data class ShopItemEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "image")
    val img: String,
    val price: String,
    val title: String,
    @ColumnInfo(name = "Favourite")
    val favorite: Boolean
)
