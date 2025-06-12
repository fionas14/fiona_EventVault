package com.fionasiregar0032.eventvault.network

import com.fionasiregar0032.eventvault.model.Event
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL ="https://store.sthresearch.site/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface EventApiService {
    @GET("vault_event.php")
    suspend fun getEvent(): List<Event>
}

object EventApi {
    val service: EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }

    fun getEventUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}