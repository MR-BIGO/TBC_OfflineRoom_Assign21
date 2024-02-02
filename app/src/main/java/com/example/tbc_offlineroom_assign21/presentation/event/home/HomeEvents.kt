package com.example.tbc_offlineroom_assign21.presentation.event.home

sealed class HomeEvents {
    data class SectionItemPressed(val id: Int) : HomeEvents()
    data class HeartPressed(val id: Int) : HomeEvents()
}