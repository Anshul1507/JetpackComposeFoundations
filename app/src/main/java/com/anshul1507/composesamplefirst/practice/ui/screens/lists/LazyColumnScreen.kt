package com.anshul1507.composesamplefirst.practice.ui.screens.lists

import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Badge
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

data class DevTask(
    val id: String,
    val title: String,
    val description: String,
    val complexity: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyColumnScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // dataset
    val dynamicTasks = remember {
        List(100) { index ->
            DevTask(
                id = "task_uuid_$index",
                title = "Feature Task #$index",
                description = "Feature description with having some long content to display here...",
                complexity = if (index % 3 ==0) "High" else "Normal"
            )
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Vertical LazyColumn")
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

        //1. Core Lazy Column Implementation
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding() + 16.dp,
                bottom = innerPadding.calculateBottomPadding() + 16.dp,
                start = 16.dp,
                end = 16.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {

            //static item header
            item {
                Text(
                    text = "Active Dev BackLog (${dynamicTasks.size} Items)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            //2. High-Performance Virtualized Loop
            items(
                items = dynamicTasks,
                key = { task ->
                    task.id //Crucial -> Provide a unique key to anchor item states
                },
                contentType = { task ->
                    task.complexity // Crucial -> Explicitly define content type tags to optimize structural recycling
                }
            ) { task ->
                TaskRowCard(task)
            }
        }
    }
}

@Composable
fun TaskRowCard(
    task: DevTask,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (task.complexity == "High") {
                MaterialTheme.colorScheme.errorContainer.copy(alpha = 0.2f)
            } else {
                MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
            }
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = task.title,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )

                Badge(
                    containerColor = if (task.complexity == "High") MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.secondary
                ) {
                    Text(
                        text = task.complexity,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(4.dp)
            )

            Text(
                text = task.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}