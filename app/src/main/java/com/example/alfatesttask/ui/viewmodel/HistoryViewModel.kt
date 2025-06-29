package com.example.alfatesttask.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfatesttask.domain.model.BinInfoModel
import com.example.alfatesttask.domain.repository.BinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel : ViewModel() {
    private var _content = MutableStateFlow<List<BinInfoModel>>(listOf())
    val content = _content.asStateFlow()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    init {
        viewModelScope.launch {
            loadContent()
        }
    }

    private suspend fun loadContent() {
        val repository = BinRepository()
        _isLoading.value = true
        try {
            _content.value = repository.getHistory()
        } catch (e: Exception) {
            Log.e("Room error", "$e")
        }
        _isLoading.value = false
    }

}