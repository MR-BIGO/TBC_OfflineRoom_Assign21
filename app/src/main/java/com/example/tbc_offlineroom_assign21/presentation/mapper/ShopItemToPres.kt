package com.example.tbc_offlineroom_assign21.presentation.mapper

import com.example.tbc_offlineroom_assign21.domain.model.ShopItem
import com.example.tbc_offlineroom_assign21.presentation.model.ShopItemPres

fun ShopItem.toPresentation(): ShopItemPres {
    return ShopItemPres(
        id = id,
        title = title,
        img = img,
        isFavourite = isFavourite,
        price = price
    )
}