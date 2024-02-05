package com.example.tbc_offlineroom_assign21.data.remote.service

import com.example.tbc_offlineroom_assign21.data.remote.model.ShopItemDto
import retrofit2.Response
import retrofit2.http.GET

interface IGetItemsService {
    @GET("df8d4951-2757-45aa-8f60-bf1592a090ce")
    suspend fun getItems(): Response<List<ShopItemDto>>
}