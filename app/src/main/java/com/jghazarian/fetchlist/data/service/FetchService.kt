package com.jghazarian.fetchlist.data.service

import com.jghazarian.fetchlist.data.model.Item
import retrofit2.http.GET

interface FetchService {
    @GET("hiring.json")
    suspend fun getItems(): List<Item>
}