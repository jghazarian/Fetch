package com.jghazarian.fetchlist.data.api

import com.jghazarian.fetchlist.data.model.Item
import com.jghazarian.fetchlist.data.service.FetchService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class FetchApi {
    var service: FetchService
    val endpoint: String = "https://fetch-hiring.s3.amazonaws.com/"

    init {
        //TODO: don't think I need the interceptor, would be helpful for logging and status messages
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BASIC
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()

        val moshi = Moshi.Builder()
            .addLast(KotlinJsonAdapterFactory())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(endpoint)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

        service = retrofit.create(FetchService::class.java)
    }

    fun getItems(): Flow<List<Item>> = flow {
        emit(service.getItems())
    }
}