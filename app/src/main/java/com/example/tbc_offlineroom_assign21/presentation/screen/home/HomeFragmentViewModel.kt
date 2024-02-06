package com.example.tbc_offlineroom_assign21.presentation.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tbc_offlineroom_assign21.data.Resource
import com.example.tbc_offlineroom_assign21.domain.use_case.GetShopItemsFilterUseCase
import com.example.tbc_offlineroom_assign21.domain.use_case.GetShopItemsUseCase
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
class HomeFragmentViewModel @Inject constructor(
    private val getItems: GetShopItemsUseCase,
    private val getItemsFilter: GetShopItemsFilterUseCase
) :
    ViewModel() {

    private val _homeState = MutableStateFlow(HomeState())
    val homeState: SharedFlow<HomeState> = _homeState.asStateFlow()

    init {
        setUpSections()
        setUpShopItems()
    }

    fun onEvent(event: HomeEvents) {
        when (event) {
            is HomeEvents.SectionItemPressed -> selectSection(event.id, event.filter)
            is HomeEvents.HeartPressed -> heartPressed(event.id)
            is HomeEvents.ResetError -> setError(null)
            is HomeEvents.RefreshPressed -> setUpShopItems()
        }
    }

    private fun heartPressed(id: Int) {
        val data = _homeState.value.data
        for (i in data!!){
            if(i.id == id) {
                i.isFavourite = !i.isFavourite
                break
            }
        }
        _homeState.update { currentState -> currentState.copy(data = data) }
    }

    private fun selectSection(id: Int, filter: String) {
        val data = _homeState.value.sections
        for (i in data!!) {
            if (i.isSelected) {
                i.isSelected = false
                break
            }
        }
        data[id].isSelected = true
        _homeState.update { currentState -> currentState.copy(sections = data) }
        if (filter == "All"){
            setUpShopItems()
        }else{
            getFilterItems(filter)
        }
    }

    private fun setUpSections() {
        viewModelScope.launch {
            getItems().collect {
                when (it) {
                    is Resource.Success -> {
                        var id = 0
                        val sections = it.data.map { item -> item.category }.distinct()
                        val uniqueSections =
                            mutableListOf("All").apply { addAll(sections) }
                        val updatedSections: MutableList<Section> = mutableListOf()
                        uniqueSections.forEach { title ->
                            updatedSections.add(
                                Section(
                                    id,
                                    title,
                                    false
                                )
                            )
                            id += 1
                        }
                        updatedSections[0].isSelected = true
                        _homeState.update { currentState ->
                            currentState.copy(
                                sections = updatedSections
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }

                    is Resource.Error -> setError(it.error)
                }
            }
        }
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

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }

                    is Resource.Error -> setError(it.error)
                }
            }
        }
    }

    private fun getFilterItems(filter: String){
        viewModelScope.launch {
            getItemsFilter(filter).collect {
                when (it) {
                    is Resource.Success -> {
                        _homeState.update { currentState ->
                            currentState.copy(
                                data = it.data.map { item -> item.toPresentation() }
                            )
                        }
                    }

                    is Resource.Loading -> {
                        _homeState.update { currentState -> currentState.copy(loading = it.loading) }
                    }

                    is Resource.Error -> setError(it.error)
                }
            }
        }
    }

    private fun setError(error: String?) {
        _homeState.update { currentState -> currentState.copy(error = error) }
    }
}