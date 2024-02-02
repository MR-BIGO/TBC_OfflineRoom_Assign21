package com.example.tbc_offlineroom_assign21.data.remote.service

import com.example.tbc_offlineroom_assign21.data.remote.model.ShopItemDto
import retrofit2.Response
import retrofit2.http.GET

interface IGetItemsService {
    @GET("1775d634-92dc-4c32-ae71-1707b8cfee41")
    suspend fun getItems(): Response<List<ShopItemDto>>
}