package com.example.tbc_offlineroom_assign21.domain.use_case

import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import com.example.tbc_offlineroom_assign21.domain.repository.ILocalShopItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShopItemsFilterUseCase @Inject constructor(private val repository: ILocalShopItemsRepository) {

    suspend operator fun invoke(filter: String): Flow<Resource<List<ShopItem>>> {
        return flow {
            emit(Resource.Loading(true))
            try {
                emit(Resource.Success(repository.getItemsFilter(filter)))
            }catch (e: Throwable){
                emit(Resource.Error(e.message.toString()))
            }
            emit(Resource.Loading(false))
        }
    }
}