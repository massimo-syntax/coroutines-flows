package com.example.featurelist.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Waves
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.SuggestionChipDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import coil.compose.AsyncImage
import com.example.featurelist.ui.viewmodel.UiState

@Composable
fun NavigationLazyColumn(
    navigateTo: (navKey: NavKey) -> Unit,
    listState: UiState
) {
    // for now category is selected, the list is already filtered in the viewmodel
    // so the check is enough if notEmpty()
    val coroutinesItems = listState.itemsList.filter { it.type == SelectedCategory.Coroutines }
    val flowItems = listState.itemsList.filter { it.type == SelectedCategory.Flows }

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        // SECTION 1: COROUTINES
        if (coroutinesItems.isNotEmpty()) {
            stickyHeader {
                SectionHeader(
                    title = "Coroutines",
                    subtitle = "Core Concurrency Utilities",
                    icon = Icons.Default.Bolt,
                    badgeColor = Color(0xFF6750A4)
                )
            }

            items(
                items = coroutinesItems,
                key = { it.hashCode() }
            ) {
                FancyListItem(
                    title = it.title,
                    subtitle = it.description,
                    description = "Start lightweight coroutines without blocking threads. Returns Job or Deferred<T>.",
                    imageUrl = "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?w=150",
                    tags = listOf("Job", "Deferred", "Scope"),
                    onClick = { navigateTo(it.navKey) }
                )
            }

        }

        // SECTION 2: FLOWS
        if (flowItems.isNotEmpty()) {
            stickyHeader {
                SectionHeader(
                    title = "Flows",
                    subtitle = "Core Reactive Stream Patterns",
                    icon = Icons.Default.Waves,
                    badgeColor = Color(0xFF006A60)
                )
            }

            items(
                items = flowItems,
                key = { it.hashCode() }
            ) {
                FancyListItem(
                    title = it.title,
                    subtitle = it.description,
                    description = "Start lightweight coroutines without blocking threads. Returns Job or Deferred<T>.",
                    imageUrl = "https://images.unsplash.com/photo-1618005182384-a83a8bd57fbe?w=150",
                    tags = listOf("Job", "Deferred", "Scope"),
                    onClick = { navigateTo(it.navKey) }
                )
            }


        }
    }

}


@Composable
private fun SectionHeader(
    title: String,
    subtitle: String,
    icon: ImageVector,
    badgeColor: Color
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFFF8F9FA))
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(8.dp),
            color = badgeColor.copy(alpha = 0.12f),
            modifier = Modifier.size(32.dp)
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    icon,
                    contentDescription = null,
                    tint = badgeColor,
                    modifier = Modifier.size(18.dp)
                )
            }
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1D1B20)
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.labelSmall,
                color = Color(0xFF49454F)
            )
        }
    }
}


@Composable
private fun FancyListItem(
    title: String,
    subtitle: String,
    description: String,
    imageUrl: String,
    tags: List<String>,
    onClick: () -> Unit
) {
    var isFavorite by remember { mutableStateOf(false) }

    ElevatedCard(
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.elevatedCardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.5.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                // Small Picture / Icon Thumbnail
                AsyncImage(
                    model = imageUrl,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(46.dp)
                        .clip(RoundedCornerShape(12.dp))
                        .background(Color(0xFFF3EDF7))
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = title,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1D1B20)
                    )
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF6750A4),
                        fontWeight = FontWeight.Medium
                    )
                }

                IconButton(
                    onClick = { isFavorite = !isFavorite },
                    modifier = Modifier.size(32.dp)
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                        contentDescription = "Favorite",
                        tint = if (isFavorite) Color(0xFFB3261E) else Color(0xFF79747E),
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = Color(0xFF49454F),
                maxLines = 2
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Tags Row
            Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                tags.forEach { tag ->
                    SuggestionChip(
                        onClick = { },
                        label = { Text(tag, style = MaterialTheme.typography.labelSmall) },
                        colors = SuggestionChipDefaults.suggestionChipColors(
                            containerColor = Color(0xFFF3EDF7)
                        ),
                        border = null,
                        modifier = Modifier.height(26.dp)
                    )
                }
            }
        }
    }
}