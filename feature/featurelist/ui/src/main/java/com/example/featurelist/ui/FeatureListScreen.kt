package com.example.presentation.featureslist

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import com.example.feature.coroutines.api.VeryComplicatedCoroutine
import com.example.feature.coroutines.api.VerySimpleCoroutine
import com.example.flows.api.VeryFastFlow
import com.example.flows.api.VerySimpleFLow
import com.example.myfetaure.api.MyFeatureRoute

@Composable
fun FeatureListScreen(
    onNavigate: (navKey: NavKey) -> Unit
){
    LazyColumn(
        Modifier.fillMaxSize().statusBarsPadding()
    ) {
        // myFeature
        featureListItem("MyFeature"){ onNavigate(MyFeatureRoute) }
        // coroutines
        featureListItem("Simple coroutine"){ onNavigate(VerySimpleCoroutine) }
        featureListItem("Complicated coroutine"){ onNavigate(VeryComplicatedCoroutine) }
        // flows
        featureListItem("Simple flow"){ onNavigate(VerySimpleFLow) }
        featureListItem("Fast flow"){ onNavigate(VeryFastFlow) }
    }
}


fun LazyListScope.featureListItem(
    title: String,
    description: String = "",
    imageUrl: String = "",
    onClick: () -> Unit = {}
){
     item{
        FancyListItem(
            title,
            description,
            imageUrl,
            onClick
        )
    }

}