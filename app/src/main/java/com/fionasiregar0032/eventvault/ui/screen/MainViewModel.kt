package com.fionasiregar0032.eventvault.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.fionasiregar0032.eventvault.network.EventApi
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import com.fionasiregar0032.eventvault.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _data = MutableStateFlow<List<Event>>(emptyList())
    val data: StateFlow<List<Event>> = _data

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = EventApi.service.getEvent()
                _data.value = result // 🔧 assign ke stateflow
                Log.d("MainViewModel", "Data: $result")
            } catch (e: Exception) {
                Log.e("MainViewModel", "Failure: ${e.message}")
            }
        }
    }
}

