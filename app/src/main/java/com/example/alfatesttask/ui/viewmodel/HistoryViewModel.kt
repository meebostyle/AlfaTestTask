package com.example.alfatesttask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfatesttask.domain.model.BinInfoModel
import com.example.alfatesttask.domain.repository.BinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HistoryViewModel: ViewModel() {
    private var _content = MutableStateFlow<List<BinInfoModel>>(listOf())
    val content = _content.asStateFlow()

    init {
        val repository = BinRepository()
        viewModelScope.launch {
            _content.value = repository.getHistory()
        }
    }

}