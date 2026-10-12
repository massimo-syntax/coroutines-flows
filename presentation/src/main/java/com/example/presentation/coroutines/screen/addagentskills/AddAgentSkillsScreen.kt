package com.example.presentation.coroutines.screen.addagentskills

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coroutinesflows.presentation.flows.uistate.MyUIState

@Composable
fun AddAgentSkillsScreen(
    onBackPressed: () -> Unit = {},
    viewModel: AddAgentSkillsViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(text = "Add Agent Skills Coroutines Screen")

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is MyUIState.Loading -> Text("Loading agent skills...")
            is MyUIState.Error -> Text("Error: ${(state as MyUIState.Error).error}")
            is MyUIState.Success -> {
                val data = (state as MyUIState.Success).data
                data.forEach { item ->
                    Text("Title: ${item.title ?: ""}")
                    Text("Body: ${item.body ?: ""}")
                    Spacer(modifier = Modifier.height(8.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { viewModel.fetchAgentSkills() }) {
            Text("Reload Agent Skills")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Button(onClick = onBackPressed) {
            Text("Back")
        }
    }
}
