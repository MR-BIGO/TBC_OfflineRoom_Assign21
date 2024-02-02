package com.example.tbc_offlineroom_assign21.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbc_offlineroom_assign21.data.local.dao.ShopItemDao
import com.example.tbc_offlineroom_assign21.data.local.entity.ShopItemEntity

@Database(entities = [ShopItemEntity::class], version = 1)
abstract class ItemsDatabase : RoomDatabase() {
    abstract fun shopItemDao(): ShopItemDao
}