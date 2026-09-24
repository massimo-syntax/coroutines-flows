package com.example.presentation.coroutines.screen.simplecoroutine

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.model.MyData
import com.example.domain.repository.CoroutinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class SimpleCoroutineViewModel @Inject constructor(
    private val repository: CoroutinesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UiStateBasic<List<MyData>>())
    val uiState: StateFlow<UiStateBasic<List<MyData>>> = _uiState.asStateFlow()

    fun loadData() {
        viewModelScope.launch {
            _uiState.update { it.copy(loading = true) }
            try {
                val data = repository.getData()
                _uiState.value = UiStateBasic(data = data)
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, loading = false) }
            }
        }
    }

    fun networkError(){
        viewModelScope.launch {
            _uiState.value= UiStateBasic(loading = true)
            try {
                // should throw error
                repository.getError()
            } catch (e: Exception) {
                _uiState.update { it.copy(error = e.message, loading = false) }
            }
        }
    }


}


data class UiStateBasic<T>(
    val loading: Boolean = false,
    val data: T? = null,
    val error: String? = null,
)