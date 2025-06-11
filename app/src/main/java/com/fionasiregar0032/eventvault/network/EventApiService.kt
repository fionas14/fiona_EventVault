package com.fionasiregar0032.eventvault.network

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://store.sthresearch.site/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface EventApiService {
    @GET("vault_event.php")
    suspend fun getEvent(): String
}

object EventApi {
    val service: EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }
}