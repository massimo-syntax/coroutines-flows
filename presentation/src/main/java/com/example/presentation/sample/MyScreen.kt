package com.example.coroutinesflows.presentation.flows.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.coroutinesflows.presentation.flows.uistate.MyUIState
import com.example.domain.model.MyData
import com.example.presentation.sample.MyViewmodel


@Composable
fun MyScreen(
    viewModel: MyViewmodel = hiltViewModel()
){
    val data by viewModel.uiState.collectAsStateWithLifecycle()
    Column(
        Modifier.statusBarsPadding()
    ) {
        when(data){
            is MyUIState.Loading ->{ Text("Loading") }
            is MyUIState.Error ->{ Error( (data as MyUIState.Error).error ) }
            is MyUIState.Success ->{ Result((data as MyUIState.Success).data) }
        }

        Button({
            viewModel.getData()
        }) {
            Text("get data")
        }
    }

}

@Composable
fun Result(data: List<MyData>){
    Column {
        data.forEach {
            Text(it.title ?: "")
            Text(it.body ?: "")
            Text(it.dateTime.toString() ?: "")
        }
    }
}

@Composable
fun Error(message: String){
    Text("error: $message", color = Color.Red, fontSize = 20.sp)
}








