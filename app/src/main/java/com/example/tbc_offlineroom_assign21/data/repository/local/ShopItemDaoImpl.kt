package com.example.tbc_offlineroom_assign21.data.repository.local

import com.example.tbc_offlineroom_assign21.data.local.dao.ShopItemDao
import com.example.tbc_offlineroom_assign21.data.local.mapper.fromDomain
import com.example.tbc_offlineroom_assign21.data.local.mapper.toDomain
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import com.example.tbc_offlineroom_assign21.domain.repository.ILocalShopItemsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ShopItemDaoImpl @Inject constructor(private val dao: ShopItemDao) :
    ILocalShopItemsRepository {
    override suspend fun getItems(): List<ShopItem> {
        return dao.getItems().map { it.toDomain() }
    }

    override suspend fun insertItems(items: List<ShopItem>) = withContext(Dispatchers.Default) {
        dao.insert(items.map { it.fromDomain() })
    }

}