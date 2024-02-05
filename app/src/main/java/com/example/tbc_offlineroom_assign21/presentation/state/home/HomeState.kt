package com.example.tbc_offlineroom_assign21.presentation.state.home

import com.example.tbc_offlineroom_assign21.presentation.model.Section
import com.example.tbc_offlineroom_assign21.presentation.model.ShopItemPres

data class HomeState (
    val sections: List<Section>? = null,
    val data: List<ShopItemPres>? = null,
    val loading: Boolean = false,
    val error: String? = null
)