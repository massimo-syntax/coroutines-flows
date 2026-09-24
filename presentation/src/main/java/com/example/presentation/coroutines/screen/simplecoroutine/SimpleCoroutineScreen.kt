package com.example.presentation.coroutines.screen.simplecoroutine


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle


@Composable
fun SimpleCoroutineScreen(
    viewModel: SimpleCoroutineViewModel = hiltViewModel()
){

    val data by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        Modifier.fillMaxSize().statusBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        Text("Simple coroutine anissimo")

        if(data.loading){
            Text("Loading")
        }

        if(data.error != null){
            Text(data.error.toString())
        }

        if(!data.data.isNullOrEmpty()){
            data.data?.forEach {
                Text(it.toString())
            }
        }

        Button({
            viewModel.loadData()
        }){
            Text("Load data")
        }

        Button({
            viewModel.networkError()
        }){
            Text("Receive server error")
        }

    }
}