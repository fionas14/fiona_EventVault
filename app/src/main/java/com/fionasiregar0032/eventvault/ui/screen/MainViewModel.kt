package com.fionasiregar0032.eventvault.ui.screen

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fionasiregar0032.eventvault.model.Event
import com.fionasiregar0032.eventvault.network.ApiStatus
import com.fionasiregar0032.eventvault.network.EventApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.ByteArrayOutputStream

class MainViewModel : ViewModel() {

    var data = mutableStateOf(emptyList<Event>())
        private set

    var status = MutableStateFlow(ApiStatus.LOADING)
        private set

    var errorMessage = mutableStateOf<String?>(null)
        private set

    fun retrieveData(userId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            status.value = ApiStatus.LOADING
            try {
                data.value = EventApi.service.getEvent(userId)
                status.value = ApiStatus.SUCCESS
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                status.value = ApiStatus.FAILED
            }
        }
    }

    fun saveData(userId:String, nama_kegiatan: String, deskripsi_kegiatan: String, tanggal_kegiatan: String, bitmap: Bitmap){
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = EventApi.service.postEvent(
                    userId,
                    nama_kegiatan.toRequestBody("text/plain".toMediaTypeOrNull()),
                    deskripsi_kegiatan.toRequestBody("text/plain".toMediaTypeOrNull()),
                    tanggal_kegiatan.toRequestBody("text/plain".toMediaTypeOrNull()),

                    bitmap.toMultipartBody()
                )
                if (result.status == "success")
                    retrieveData(userId)
                else {
                    throw Exception(result.message)
                }
            } catch (e:Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }
    fun deleteData(userId: String, id: String) {
        viewModelScope.launch(Dispatchers.IO){
            try {
                val result = EventApi.service.deleteEvent(userId, id)
                if (result.status == "success")
                    retrieveData(userId)

                else
                    throw Exception(result.message)
            } catch (e: Exception) {
                Log.d("MainViewModel", "Failure: ${e.message}")
                errorMessage.value = "Error: ${e.message}"
            }
        }
    }

    private fun Bitmap.toMultipartBody(): MultipartBody.Part {
        val stream = ByteArrayOutputStream()
        compress(Bitmap.CompressFormat.JPEG, 80, stream)
        val byteArray = stream.toByteArray()
        val requestBody = byteArray.toRequestBody(
            "image/jpg".toMediaTypeOrNull(), 0, byteArray.size)
        return MultipartBody.Part.createFormData(
            "image", "image.jpg", requestBody
        )
    }

    fun clearMessage() { errorMessage.value=null}
}