package com.example.tbc_offlineroom_assign21.domain.use_case

import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import com.example.tbc_offlineroom_assign21.domain.repository.ILocalShopItemsRepository
import javax.inject.Inject

class InsertItemsInDBUseCase @Inject constructor(private val repository: ILocalShopItemsRepository) {

    suspend operator fun invoke(items: List<ShopItem>){
        repository.insertItems(items)
    }
}