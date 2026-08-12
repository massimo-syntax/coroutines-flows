package com.example.compose.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AvTimer
import androidx.compose.material.icons.rounded.AddRoad
import androidx.compose.material.icons.sharp.AutoAwesome
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation3.runtime.NavKey
import com.example.designsystem.components.ChipData
import com.example.designsystem.components.ModalDrawerContent
import com.example.designsystem.components.ModernAppBar
import com.example.designsystem.components.ModernChip
import com.example.designsystem.components.ModernSearchTextField
import com.example.featurelist.ui.NavigationLazyColumn
import com.example.featurelist.ui.SelectedCategory
import com.example.featurelist.ui.viewmodel.ListScreenViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationListScreen(
    viewModel: ListScreenViewModel = viewModel(),
    onNavigateToProfile: () -> Unit,
    onNavigateToSettings: () -> Unit,
    navigateToShowcaseScreen: (navKey: NavKey) -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val searchQuery by viewModel.query.collectAsStateWithLifecycle()
    val selectedCategoryFilter by viewModel.selectedCategory.collectAsStateWithLifecycle()
    val listState by viewModel.uiState.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerContent(
                onNavigateToProfile = onNavigateToProfile,
                onNavigateToSettings = onNavigateToSettings,
                closeDrawer = { scope.launch { drawerState.close() } },
            )
        }
    ) {
        Scaffold(
            containerColor = MaterialTheme.colorScheme.background, // Clean Light Mode background
            topBar = {
                ModernAppBar(
                    isDrawerOpen = drawerState.isOpen,
                    onToggleDrawer = {
                        scope.launch {
                            if (drawerState.isOpen) drawerState.close() else drawerState.open()
                        }
                    },
                    onProfileClick = onNavigateToProfile
                )
            }
        ) { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            ) {
                // Modern Clean Search Text Field
                ModernSearchTextField(
                    query = searchQuery,
                    onQueryChange = { viewModel.onQueryChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                // Category Quick Filter Chips
                CategoryFilterChipsRow(
                    selectedCategory = selectedCategoryFilter,
                    onSelectCategory = { viewModel.onCategorySelected(it) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // lazy column
                NavigationLazyColumn(
                    navigateTo = navigateToShowcaseScreen,
                    listState = listState
                )
            }
        }
    }
}


@Composable
fun CategoryFilterChipsRow(
    selectedCategory: SelectedCategory,
    onSelectCategory: (SelectedCategory) -> Unit,
    modifier: Modifier = Modifier
) {

    val chips = listOf(
        ChipData(
            label = SelectedCategory.All.name,
            icon = Icons.Sharp.AutoAwesome
        ),
        ChipData(
            label = SelectedCategory.Coroutines.name,
            icon = Icons.Rounded.AddRoad
        ),
        ChipData(
            label = SelectedCategory.Flows.name,
            icon = Icons.Outlined.AvTimer
        )
    )

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .horizontalScroll(rememberScrollState())
    ) {

        chips.forEach { chipData ->
            ModernChip(
                chipData = chipData,
                selected = selectedCategory.name == chipData.label,
                onClick = {
                    val category: SelectedCategory =
                        SelectedCategory.entries.find { it.name == chipData.label }
                            ?: SelectedCategory.All
                    onSelectCategory(category)
                },
            )
        }
    }
}
