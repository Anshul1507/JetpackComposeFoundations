package com.anshul1507.composesamplefirst.practice.ui.screens.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.collections.mapOf
import kotlin.to

data class Item(
    val id: String,
    val topic: String,
    val summary: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StickyHeaderScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // dataset
    val groupedData = remember {
        mapOf(
            "Module 1: Layout Fundamentals" to listOf(
                Item("d1", "Box Coordination", "Stacking layouts using alignment parameters."),
                Item(
                    "d2",
                    "Row & Column Scopes",
                    "Distributing weights dynamically across alignment planes."
                )
            ),
            "Module 2: State Architectures" to listOf(
                Item(
                    "d3",
                    "Remember vs RememberSaveable",
                    "Managing configuration changes and instance tracking states."
                ),
                Item(
                    "d4",
                    "SideEffect Execution",
                    "Safely managing coroutine scopes inside declarative environments."
                )
            ),
            "Module 3: Material Components" to listOf(
                Item(
                    "d5",
                    "Scaffold Infrastructure",
                    "Anchoring top bars, bottom navigations, and dynamic FAB actions."
                ),
                Item(
                    "d6",
                    "Modal Drawers",
                    "Implementing sliding navigation drawers across multiple viewport scopes."
                )
            )
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Sticky Headers")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            groupedData.forEach { (categoryHeader, itemsList) ->
                // Sticky header
                stickyHeader(key = categoryHeader) {
                    Text(
                        text = categoryHeader,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(vertical = 12.dp)
                    )
                }

                items(
                    items = itemsList,
                    key = { item -> item.id }
                ) { item ->
                    RowCard(item)
                }
            }
        }
    }
}

@Composable
fun RowCard(
    item: Item,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(
                0.3f
            )
        )
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                text = item.topic,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = item.summary,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}