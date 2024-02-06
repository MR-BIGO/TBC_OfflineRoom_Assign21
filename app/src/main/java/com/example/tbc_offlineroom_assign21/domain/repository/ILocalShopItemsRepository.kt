package com.example.tbc_offlineroom_assign21.domain.repository

import com.example.tbc_offlineroom_assign21.domain.model.ShopItem

interface ILocalShopItemsRepository {
    suspend fun getItems(): List<ShopItem>

    suspend fun insertItems(items:List<ShopItem>)

    suspend fun getItemsFilter(filter: String): List<ShopItem>
}