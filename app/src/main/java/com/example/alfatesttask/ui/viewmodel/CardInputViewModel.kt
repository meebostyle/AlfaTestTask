package com.example.alfatesttask.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.alfatesttask.domain.model.BinInfoModel
import com.example.alfatesttask.domain.repository.BinRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class CardInputViewModel : ViewModel() {

    private var _content = MutableStateFlow<BinInfoModel>(BinInfoModel())
    val content = _content.asStateFlow()

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private var _incorrectInput = mutableStateOf(false)
    val incorrectInput: State<Boolean> = _incorrectInput

    private var _textFieldValue = mutableStateOf(TextFieldValue(""))
    val textFieldValue: State<TextFieldValue> = _textFieldValue

    fun setTextFieldValue(n: TextFieldValue) {
        _textFieldValue.value = n
    }

    fun getContent(number: String) {
        _content.value = BinInfoModel()
        if (number.length in (6..8)) {
            _isLoading.value = true
            viewModelScope.launch {
                loadContent(number)
            }
        } else
            _incorrectInput.value = true

    }

    fun clearIncorrectInput() {
        _incorrectInput.value = false
    }

    private suspend fun loadContent(number: String) {
        val repository = BinRepository()
        try {
            val response = repository.getBinInfo(number)
            if (response.bank != "-")
                _content.value = response
            else
                _incorrectInput.value = true
        } catch (e: Exception) {
            Log.e("Response log", "Error: $e")
        }
        _isLoading.value = false
    }
}