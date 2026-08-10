package com.example.feature.usersettings.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.coroutinesflows.LocalAppState

@Composable
fun ProfileScreen() {
    val appState = LocalAppState.current

    Column(
        Modifier
            .fillMaxWidth()
            .statusBarsPadding()
    ) {
        Text("profile screen")
    }
}