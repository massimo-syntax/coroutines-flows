package com.example.presentation.coroutines.screen.sequentialCoroutines

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CloudDownload
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.core.preferences.LocalAppState
import com.example.domain.model.AndroidFeature

/**
 * Sequential coroutines sample screen.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SequentialCoroutinesScreen(
    modifier: Modifier = Modifier,
    viewModel: SequentialCoroutinesViewModel = hiltViewModel(),
    onBackPressed: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sequential Coroutines",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                navigationIcon = {
                    IconButton(onClick = {
                        if (uiState is SequentialCoroutinesUiState.Initial) {
                            onBackPressed()
                        } else {
                            viewModel.resetToInitial()
                        }
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    if (uiState is SequentialCoroutinesUiState.ListScreen) {
                        IconButton(onClick = { viewModel.reload() }) {
                            Icon(
                                imageVector = Icons.Default.Refresh,
                                contentDescription = "Reload"
                            )
                        }
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            when (val state = uiState) {
                is SequentialCoroutinesUiState.Initial -> {
                    InitialLoadView(
                        onMountListClick = { viewModel.mountListAndLoad() },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is SequentialCoroutinesUiState.ListScreen -> {
                    FeaturesListScreen(
                        state = state,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

/**
 * Initial view displaying code snippet and load trigger.
 */
@Composable
private fun InitialLoadView(
    onMountListClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val appState = LocalAppState.current
    val shape = RoundedCornerShape(appState.cornerRadiusValue.dp)

    Column(
        modifier = modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        PostCodeBlock(
            codeSnippet = """      
                viewModelScope.launch {
                    _uiState.value = SequentialCoroutinesUiState.ListScreen(
                        isVersionLoading = true,
                        lastAndroidVersion = null,
                        isFeaturesLoading = true,
                        features = emptyList()
                    )
                    val versions = repository.getSequentialNewestAndroidVersions()
                    val latestVersion = versions.last().title 

                    isVersionLoading = false,
                    lastAndroidVersion = latestVersion

                    val data = repository.getSequentialFeaturesNewestAndroidVersions()

                    isFeaturesLoading = false,
                    features = data
                }
            """.trimIndent(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Latest Android Innovations",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Discover modern platform capabilities, security isolation, and system APIs.",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(28.dp))

        Button(
            onClick = onMountListClick,
            shape = shape,
            contentPadding = PaddingValues(horizontal = 24.dp, vertical = 12.dp)
        ) {
            Icon(imageVector = Icons.Default.CloudDownload, contentDescription = null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Load Latest Features")
        }
    }
}

/**
 * List screen container.
 */
@Composable
private fun FeaturesListScreen(
    state: SequentialCoroutinesUiState.ListScreen,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        if (state.isVersionLoading) {
            VersionLoadingIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        } else if (state.lastAndroidVersion != null) {
            VersionSegmentBanner(
                version = state.lastAndroidVersion,
                modifier = Modifier.fillMaxWidth()
            )
        }

        if (state.isFeaturesLoading) {
            FeaturesListLoader(
                version = state.lastAndroidVersion,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        } else {
            FeaturesLazyList(
                features = state.features,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

/**
 * Lazy column of feature cards.
 */
@Composable
fun FeaturesLazyList(
    features: List<AndroidFeature>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(
            items = features,
            key = { it.id }
        ) { feature ->
            SequentialCoroutinesCard(
                feature = feature,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Version banner component.
 */
@Composable
fun VersionSegmentBanner(
    version: String,
    modifier: Modifier = Modifier
) {
    val appState = LocalAppState.current
    val shape = RoundedCornerShape(appState.cornerRadiusValue.dp)
    Surface(
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.7f),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Last Android Version: $version",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Surface(
                shape = shape,
                color = MaterialTheme.colorScheme.primaryContainer
            ) {
                Text(
                    text = "API $version",
                    style = MaterialTheme.typography.labelSmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 3.dp)
                )
            }
        }
    }
}

/**
 * Version banner loader component.
 */
@Composable
fun VersionLoadingIndicator(
    modifier: Modifier = Modifier
) {
    val appState = LocalAppState.current
    val shape = RoundedCornerShape(appState.cornerRadiusValue.dp)
    Surface(
        shape = shape,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f),
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 14.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            CircularProgressIndicator(
                modifier = Modifier.size(16.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = "Fetching newest Android version...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

/**
 * Features list loader component.
 */
@Composable
fun FeaturesListLoader(
    version: String?,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(36.dp),
            strokeWidth = 3.dp,
            color = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = if (version != null)
                "Loading Android $version innovations..."
            else
                "Waiting for version, then loading features...",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

/**
 * Formatted code block card component.
 */
@Composable
fun PostCodeBlock(
    codeSnippet: String,
    modifier: Modifier = Modifier,
    languageLabel: String = "Kotlin"
) {
    val appState = LocalAppState.current
    val shape = RoundedCornerShape(appState.cornerRadiusValue.dp)

    Surface(
        shape = shape,
        color = Color(0xFF1E1E2E),
        modifier = modifier.border(
            width = 1.dp,
            color = Color(0xFF313244),
            shape = shape
        )
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFF181825))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(6.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(modifier = Modifier.size(8.dp).clip(shape).background(Color(0xFFF38BA8)))
                    Box(modifier = Modifier.size(8.dp).clip(shape).background(Color(0xFFF9E2AF)))
                    Box(modifier = Modifier.size(8.dp).clip(shape).background(Color(0xFFA6E3A1)))
                }
                Text(
                    text = languageLabel,
                    style = MaterialTheme.typography.labelSmall,
                    color = Color(0xFFA6ADC8),
                    fontFamily = FontFamily.Monospace,
                    fontWeight = FontWeight.Medium
                )
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .horizontalScroll(rememberScrollState())
                    .padding(14.dp)
            ) {
                Text(
                    text = codeSnippet,
                    color = Color(0xFFCDD6F4),
                    fontFamily = FontFamily.Monospace,
                    fontSize = 12.5.sp,
                    lineHeight = 18.sp
                )
            }
        }
    }
}
