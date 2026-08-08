package com.example.compose.ui.screens

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.designsystem.components.ModalDrawerContent
import com.example.designsystem.components.ModernAppBar
import com.example.designsystem.components.ModernSearchTextField
import com.example.featurelist.ui.NavigationLazyColumn
import kotlinx.coroutines.launch



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationListScreen(
    onNavigateToProfile: () -> Unit = {},
    onNavigateToSettings: () -> Unit = {},
    navigateToShowcaseScreen: (navKey: NavKey) -> Unit,
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    var searchQuery by remember { mutableStateOf("") }
    var selectedCategoryFilter by remember { mutableStateOf(SelectedCategory.All) }

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
                    query = searchQuery,
                    onQueryChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )

                // Category Quick Filter Chips
                CategoryFilterChipsRow(
                    selectedCategory = selectedCategoryFilter,
                    onSelectCategory = { selectedCategoryFilter = it }
                )

                Spacer(modifier = Modifier.height(8.dp))

                // lazy column
                NavigationLazyColumn(
                    selectedCategory = selectedCategoryFilter,
                    navigateTo = navigateToShowcaseScreen,
                    searchQuery = searchQuery
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
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .horizontalScroll(rememberScrollState())
    ) {
        FilterChip(
            selected = selectedCategory == SelectedCategory.All,
            onClick = { onSelectCategory(SelectedCategory.All) },
            label = { Text("All") },
            leadingIcon = { Icon(Icons.Default.AllInclusive, contentDescription = null, modifier = Modifier.size(16.dp)) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFF1D1B20),
                selectedLabelColor = Color.White,
                selectedLeadingIconColor = Color(0xFFFFD700)
            )
        )

        FilterChip(
            selected = selectedCategory == SelectedCategory.Coroutines,
            onClick = { onSelectCategory(SelectedCategory.Coroutines) },
            label = { Text("Coroutines") },
            leadingIcon = { Icon(Icons.Default.Bolt, contentDescription = null, modifier = Modifier.size(16.dp)) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFF0061A4),
                selectedLabelColor = Color.White,
                selectedLeadingIconColor = Color.White
            )
        )

        FilterChip(
            selected = selectedCategory == SelectedCategory.Flows,
            onClick = { onSelectCategory(SelectedCategory.Flows) },
            label = { Text("Flows") },
            leadingIcon = { Icon(Icons.Default.Waves, contentDescription = null, modifier = Modifier.size(16.dp)) },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = Color(0xFF006A60),
                selectedLabelColor = Color.White,
                selectedLeadingIconColor = Color.White
            )
        )
    }
}

enum class SelectedCategory{
    Coroutines,
    Flows,
    All
}
