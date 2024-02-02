package com.example.tbc_offlineroom_assign21.domain.use_case

import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import com.example.tbc_offlineroom_assign21.domain.repository.IShopItemsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetRemoteShopItemsUseCase @Inject constructor(private val repository: IShopItemsRepository) {

    suspend operator fun invoke(): Flow<Resource<List<ShopItem>>> {
        return repository.getShopItems()
    }
}