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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SequentialCoroutinesScreen(
    modifier: Modifier = Modifier,
    viewModel: FeaturesViewModel = hiltViewModel(),
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
                            if (uiState is FeaturesUiState.Initial){
                                onBackPressed()
                            } else { // FeaturesUiState.ListScreen
                                viewModel.resetToInitial()
                            }
                        }) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = "Back to Intro"
                            )
                        }
                },
                actions = {
                    if (uiState is FeaturesUiState.ListScreen) {
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
                is FeaturesUiState.Initial -> {
                    // Screen 1: Initial view with code block & button
                    InitialLoadView(
                        onMountListClick = { viewModel.mountListAndLoad() },
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is FeaturesUiState.ListScreen -> {
                    // Screen 2: Standalone Version Segment-Banner + FeaturesLazyList
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
 * InitialLoadView
 * Uses RoundedCornerShape(appState.cornerRadiusValue) for code card and button
 */
@Composable
private fun InitialLoadView(
    onMountListClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val appState = LocalAppState.current
    val shape = RoundedCornerShape(appState.cornerRadiusValue)

    Column(
        modifier = modifier
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Post-style formatted code block with appState corner shape
        PostCodeBlock(
            codeSnippet = """      
                viewModelScope.launch {
                    // Mount the list screen with both loaders active
                    _uiState.value = FeaturesUiState.ListScreen(
                        isVersionLoading = true,
                        lastAndroidVersion = null,
                        isFeaturesLoading = true,
                        features = emptyList()
                    )
                    // Step 1: Sequential call to fetch newest versions & take last
                    val versions = repository.getSequentialNewestAndroidVersions()
                    val latestVersion = versions.last().title 
    
                    // State update 1: Version loaded (Version loader clears -> Version banner displays)   
                    isVersionLoading = false,
                    lastAndroidVersion = latestVersion
                       
                    // Step 2: Sequential call to fetch features (runs with version already visible)
                    val data = repository.getSequentialFeaturesNewestAndroidVersions()
    
                    // State update 2: Features loaded (List loader clears -> cards display)
                    isFeaturesLoading = false,
                    features = data
                }
            """.trimIndent(),
            shape = shape,
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
 * FeaturesListScreen
 * Houses the Standalone Version Segment-Banner OUTSIDE the LazyColumn,
 * then delegates rendering the items to FeaturesLazyList which takes just the List<AndroidFeature>.
 */
@Composable
private fun FeaturesListScreen(
    state: FeaturesUiState.ListScreen,
    modifier: Modifier = Modifier
) {
    val appState = LocalAppState.current
    val shape = RoundedCornerShape(appState.cornerRadiusValue)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // [STANDALONE SEGMENT-BANNER] Sits outside the LazyColumn!
        if (state.isVersionLoading) {
            VersionLoadingIndicator(
                shape = shape,
                modifier = Modifier.fillMaxWidth()
            )
        } else if (state.lastAndroidVersion != null) {
            VersionSegmentBanner(
                version = state.lastAndroidVersion,
                shape = shape,
                modifier = Modifier.fillMaxWidth()
            )
        }

        // [LAZY LIST SECTION]
        if (state.isFeaturesLoading) {
            FeaturesListLoader(
                version = state.lastAndroidVersion,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        } else {
            // Lazy list only takes the List<AndroidFeature> parameter!
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
 * FeaturesLazyList
 * Directly takes features: List<AndroidFeature> as its parameter (not state.ListScreen).
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
            FeatureCard(
                feature = feature,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

/**
 * Standalone Segment-Banner for the newest Android Version
 */
@Composable
fun VersionSegmentBanner(
    version: String,
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier
) {
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
 * Loader for the standalone Version segment-banner
 */
@Composable
fun VersionLoadingIndicator(
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier
) {
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
 * Loader for the features list
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
 * PostCodeBlock: Formatted code card styled with the given shape
 */
@Composable
fun PostCodeBlock(
    codeSnippet: String,
    shape: RoundedCornerShape,
    modifier: Modifier = Modifier,
    languageLabel: String = "Kotlin"
) {
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