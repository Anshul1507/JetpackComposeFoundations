package com.anshul1507.composesamplefirst.practice.ui.screens.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class GridComponentItem(
    val id: String,
    val title: String,
    val isHeaderBanner: Boolean
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyGridScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // dataset
    val gridItems = remember {
        listOf(
            GridComponentItem("g1", "Master Architecture Console", isHeaderBanner = true),
            GridComponentItem("g2", "Database Nodes", isHeaderBanner = false),
            GridComponentItem("g3", "Ktor API Router", isHeaderBanner = false),
            GridComponentItem("g4", "Redis Cache Sync", isHeaderBanner = false),
            GridComponentItem("g5", "WorkManager Jobs", isHeaderBanner = false),
            GridComponentItem("g6", "Secondary Analytics Overview", isHeaderBanner = true),
            GridComponentItem("g7", "VIX Index Feed", isHeaderBanner = false),
            GridComponentItem("g8", "Telemetry Logs", isHeaderBanner = false)
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Spanning Grid Layout")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        //1. Fixed 2-Column Grid Layout Structure
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(
                items = gridItems,
                key = {_, item ->
                    item.id
                },
                span = {_, item ->
                    if (item.isHeaderBanner) {
                        // expand to capture the maximum available column space (full width)
                        GridItemSpan(maxLineSpan)
                    } else {
                        // restrict cell to capture exactly 1 standard grid unit size
                        GridItemSpan(1)
                    }
                }
            ) { _, item ->
                if (item.isHeaderBanner) {
                    BannerItemCard(item.title)
                } else {
                    GridCellCard(item.title)
                }

            }
        }
    }
}

@Composable
fun BannerItemCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Text(
            text = title,
            modifier = Modifier.padding(24.dp),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun GridCellCard(
    title: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth().height(120.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
    ) {
        Box(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            contentAlignment = Alignment.BottomStart
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}