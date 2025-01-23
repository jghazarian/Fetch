package com.jghazarian.fetchlist.data.api

import android.util.Log
import com.jghazarian.fetchlist.data.model.Item
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FetchApiKtor : FetchApi {
    override val endpoint: String = "https://fetch-hiring.s3.amazonaws.com/"

    private var client: HttpClient = HttpClient(CIO) {
        defaultRequest {
            url(endpoint)
        }
        install(ContentNegotiation) {
            json()
        }
    }

    override fun getItems(): Flow<List<Item>> = flow {
        val items: List<Item> = client.get("hiring.json").body()
        emit(items)
    }
}