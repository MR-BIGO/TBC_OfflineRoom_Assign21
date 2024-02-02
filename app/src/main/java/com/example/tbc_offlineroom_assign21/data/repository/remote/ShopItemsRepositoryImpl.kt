package com.example.tbc_offlineroom_assign21.data.repository.remote

import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.data.remote.common.HandleResponse
import com.example.tbc_offlineroom_assign21.data.remote.mapper.mapListToDomain
import com.example.tbc_offlineroom_assign21.data.remote.mapper.toDomain
import com.example.tbc_offlineroom_assign21.data.remote.service.IGetItemsService
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import com.example.tbc_offlineroom_assign21.domain.repository.IShopItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ShopItemsRepositoryImpl @Inject constructor(
    private val service: IGetItemsService,
    private val handler: HandleResponse
) : IShopItemsRepository {
    override suspend fun getShopItems(): Flow<Resource<List<ShopItem>>> {
        return handler.safeApiCall {
            service.getItems()
        }.mapListToDomain { it.toDomain() }
    }
}