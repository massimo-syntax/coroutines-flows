package com.example.presentation.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesflows.presentation.flows.uistate.MyUIState
import com.example.domain.model.MyData
import com.example.domain.usecase.GetSomeDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class MyViewmodel @Inject constructor(
    private val getSomeDataUseCase: GetSomeDataUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyUIState<List<MyData>>>(MyUIState.Loading)
    val uiState = _uiState.asStateFlow()


    fun getData(){
        viewModelScope.launch {
            try {
                _uiState.value = MyUIState.Success(
                    getSomeDataUseCase()
                )
            }catch (e: Exception){
                _uiState.value = MyUIState.Error( e.message.toString() )
            }

        }
    }

}