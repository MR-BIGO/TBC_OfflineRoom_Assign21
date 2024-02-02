package com.example.tbc_offlineroom_assign21.di

import com.example.tbc_offlineroom_assign21.data.remote.common.HandleResponse
import com.example.tbc_offlineroom_assign21.data.remote.service.IGetItemsService
import com.example.tbc_offlineroom_assign21.data.repository.remote.ShopItemsRepositoryImpl
import com.example.tbc_offlineroom_assign21.domain.repository.IShopItemsRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideGetShopItemsRepository(
        getService: IGetItemsService,
        handler: HandleResponse
    ): IShopItemsRepository {
        return ShopItemsRepositoryImpl(getService, handler)
    }

    @Singleton
    @Provides
    fun provideHandleResponse(): HandleResponse {
        return HandleResponse()
    }
}