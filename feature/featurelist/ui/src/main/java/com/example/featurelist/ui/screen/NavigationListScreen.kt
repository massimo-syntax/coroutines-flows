package com.example.compose.ui.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.AvTimer
import androidx.compose.material.icons.rounded.AddRoad
import androidx.compose.material.icons.sharp.AutoAwesome
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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


    var searchQuery = viewModel.query.collectAsStateWithLifecycle()
    var selectedCategoryFilter = viewModel.selectedCategory.collectAsStateWithLifecycle()
    var listState = viewModel.uiState.collectAsStateWithLifecycle()

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
            containerColor = Color(0xFFF8F9FA), // Clean Light Mode background
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
                    query = searchQuery.value,
                    onQueryChange = { viewModel.onQueryChanged(it) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                // Category Quick Filter Chips
                CategoryFilterChipsRow(
                    selectedCategory = selectedCategoryFilter.value,
                    onSelectCategory = { viewModel.onCategorySelected(it) }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // lazy column
                NavigationLazyColumn(
                    navigateTo = navigateToShowcaseScreen,
                    listState = listState.value
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
            icon = Icons.Sharp.AutoAwesome,
            selectedColor = Color(0xFF6C63FF)
        ),
        ChipData(
            label = SelectedCategory.Coroutines.name,
            icon = Icons.Rounded.AddRoad,
            selectedColor = Color(0xFFFF5C7A)
        ),
        ChipData(
            label = SelectedCategory.Flows.name,
            icon = Icons.Outlined.AvTimer,
            selectedColor = Color(0xFF00A896)
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
