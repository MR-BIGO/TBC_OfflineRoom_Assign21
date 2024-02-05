package com.example.tbc_offlineroom_assign21.data.local.mapper

import com.example.tbc_offlineroom_assign21.data.local.entity.ShopItemEntity
import com.example.tbc_offlineroom_assign21.domain.model.ShopItem

fun ShopItem.fromDomain() : ShopItemEntity{
    return ShopItemEntity(
        id = id,
        img = img,
        price = price,
        title = title,
        favorite = isFavourite,
        category = category
    )
}