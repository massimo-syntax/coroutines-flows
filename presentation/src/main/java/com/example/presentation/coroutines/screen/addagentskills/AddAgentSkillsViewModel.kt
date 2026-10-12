package com.example.presentation.coroutines.screen.addagentskills

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coroutinesflows.presentation.flows.uistate.MyUIState
import com.example.domain.model.MyData
import com.example.domain.repository.CoroutinesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel
class AddAgentSkillsViewModel @Inject constructor(
    private val repository: CoroutinesRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<MyUIState<List<MyData>>>(MyUIState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchAgentSkills()
    }

    fun fetchAgentSkills() {
        viewModelScope.launch {
            try {
                _uiState.value = MyUIState.Success(repository.addAgentSkills())
            } catch (e: Exception) {
                _uiState.value = MyUIState.Error(e.message ?: "Error fetching agent skills")
            }
        }
    }
}
