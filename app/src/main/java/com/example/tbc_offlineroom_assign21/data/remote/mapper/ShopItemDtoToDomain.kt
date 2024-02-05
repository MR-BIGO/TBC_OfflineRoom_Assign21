package com.example.tbc_offlineroom_assign21.data.remote.mapper

import com.example.tbc_offlineroom_assign21.data.remote.model.ShopItemDto
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem

fun ShopItemDto.toDomain(): ShopItem {
    return ShopItem(
        id = id,
        img = img,
        price = price,
        title = title,
        isFavourite = isFavourite,
        category = category
    )
}