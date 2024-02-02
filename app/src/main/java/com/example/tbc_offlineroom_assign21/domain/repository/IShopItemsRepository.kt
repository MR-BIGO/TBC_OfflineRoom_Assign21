package com.example.tbc_offlineroom_assign21.domain.repository

import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import kotlinx.coroutines.flow.Flow

interface IShopItemsRepository {

    suspend fun getShopItems(): Flow<Resource<List<ShopItem>>>
}