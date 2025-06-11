package com.fionasiregar0032.eventvault.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fionasiregar0032.eventvault.network.EventApi
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = EventApi.service.getEvent()
                Log.d("MainViewModel", "Data: $result")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failure: ${e.message}")

            }
        }
    }
}
