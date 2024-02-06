package com.example.tbc_offlineroom_assign21.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.data.local.entity.ShopItemEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ShopItemDao {

    @Query("SELECT * FROM items")
    suspend fun getItems(): List<ShopItemEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: List<ShopItemEntity>)

    @Query("SELECT * FROM items WHERE category LIKE :filter")
    suspend fun getItemsFilter(filter: String): List<ShopItemEntity>
}