package com.example.tbc_offlineroom_assign21.domain.use_case

import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.data.remote.common.NetworkChecker
import com.example.tbc_offlineroom_assign21.data.remote.common.NetworkStatusTracker
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import com.example.tbc_offlineroom_assign21.domain.repository.ILocalShopItemsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetShopItemsUseCase @Inject constructor(
    private val networkChecker: NetworkStatusTracker,
    private val remoteShopItemsUseCase: GetRemoteShopItemsUseCase,
    private val insertItemsInDBUseCase: InsertItemsInDBUseCase,
    private val repository: ILocalShopItemsRepository
) {

    suspend operator fun invoke(): Flow<Resource<List<ShopItem>>> {
        return flow {
            emit(Resource.Loading(true))

            if (networkChecker.isConnected()) {
                remoteShopItemsUseCase().collect {
                    when (it) {
                        is Resource.Success -> {
                            insertItemsInDBUseCase(it.data)
                        }

                        is Resource.Loading -> {}
                        is Resource.Error -> {
                            emit(Resource.Error(it.error))
                        }
                    }
                }
            }

            emit(Resource.Success(repository.getItems()))

            emit(Resource.Loading(false))
        }
    }
}