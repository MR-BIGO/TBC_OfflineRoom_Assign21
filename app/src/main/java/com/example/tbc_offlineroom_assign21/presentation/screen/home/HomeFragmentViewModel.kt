package com.example.tbc_offlineroom_assign21.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.domain.use_case.GetRemoteShopItemsUseCase
import com.example.tbc_offlineroom_assign21.presentation.event.home.HomeEvents
import com.example.tbc_offlineroom_assign21.presentation.mapper.toPresentation
import com.example.tbc_offlineroom_assign21.presentation.model.Section
import com.example.tbc_offlineroom_assign21.presentation.state.home.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeFragmentViewModel @Inject constructor(private val getItems: GetRemoteShopItemsUseCase) :
    ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()

    init {
        setUpSections()
        setUpShopItems()
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.SectionItemPressed -> selectSection(event.id)
            is HomeEvents.HeartPressed -> heartPressed(event.id)
        }
    }

    private fun heartPressed(id: Int) {
        val data = _homeState.value.data
        data!![id - 1].isFavourite = !data[id - 1].isFavourite
        _homeState.update { currentState -> currentState.copy(data = data) }
    }

    private fun selectSection(id: Int) {
        val data = _homeState.value.sections
        for (i in data!!) {
            if (i.isSelected) {
                i.isSelected = false
                break
            }
        }
        data[id].isSelected = true
        _homeState.update { currentState -> currentState.copy(sections = data) }
    }

    private fun setUpSections() {
        var data: MutableList<Section>? = mutableListOf()
        data?.apply {
            add(Section(0, "All", true))
            add(Section(1, "Party", false))
            add(Section(2, "Camping", false))
            add(Section(3, "Casual", false))
        }
        _homeState.update { currentState -> currentState.copy(sections = data) }
        data = null
    }

    private fun setUpShopItems() {
        viewModelScope.launch {
            getItems().collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                data = it.data.map { item -> item.toPresentation() }
                            )
                        }
                    }

                    is Resource.Loading -> {}
                    is Resource.Error -> {}
                }
            }
        }
    }
}