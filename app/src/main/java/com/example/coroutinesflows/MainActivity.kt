package com.example.coroutinesflows

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.coroutinesflows.designsystem.theme.CoroutinesFlowsTheme
import com.example.coroutinesflows.presentation.flows.screen.MyScreen
import com.example.navigation.Nav

import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CoroutinesFlowsTheme {
                 Nav()
            }
        }
    }
}

