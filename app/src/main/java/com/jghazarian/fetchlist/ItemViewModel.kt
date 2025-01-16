package com.jghazarian.fetchlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jghazarian.fetchlist.data.api.FetchApi
import com.jghazarian.fetchlist.data.model.Item
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ItemsViewModel : ViewModel() {
    private val api: FetchApi = FetchApi()     //In a real world larger project this should exist globally and be injected

    val uiStateFlow: StateFlow<ItemsUiState> =
        api.getItems().map { ItemsUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000L),
                initialValue = ItemsUiState()
            )
}

data class ItemsUiState(val items: List<Item> = listOf())