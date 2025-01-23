package com.jghazarian.fetchlist.data.api

import com.jghazarian.fetchlist.data.model.Item
import kotlinx.coroutines.flow.Flow

interface FetchApi {
    val endpoint: String

    //TODO: would be helpful to wrap this into a result class that can emit failures
    fun getItems() : Flow<List<Item>>
}