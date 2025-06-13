package com.fionasiregar0032.eventvault.network

import com.fionasiregar0032.eventvault.model.Event
import com.fionasiregar0032.eventvault.model.OpStatus
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

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
    suspend fun getEvent(
        @Header("Authorization") userId: String
    ): List<Event>

    @Multipart
    @POST("vault_event.php")
    suspend fun postEvent(
        @Header("Authorization") userId: String,
        @Part("nama_kegiatan") nama_kegiatan: RequestBody,
        @Part("deskripsi_kegiatan") deskripsi_kegiatan: RequestBody,
        @Part("tanggal_kegiatan") tanggal_kegiatan: RequestBody,
        @Part image: MultipartBody.Part
    ): OpStatus

    @DELETE("vault_event.php")
    suspend fun deleteEvent(
        @Header("Authorization") userId: String,
        @Query("id") id: String
    ): OpStatus
}

object EventApi {
    val service: EventApiService by lazy {
        retrofit.create(EventApiService::class.java)
    }

    fun getEventUrl(imageId: String): String {
        return "$BASE_URL$imageId.jpg"
    }
}
enum class ApiStatus { LOADING, SUCCESS, FAILED}