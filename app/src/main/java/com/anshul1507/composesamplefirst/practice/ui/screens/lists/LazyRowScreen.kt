package com.anshul1507.composesamplefirst.practice.ui.screens.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Analytics
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

data class MetricInsight(
    val id: String,
    val title: String,
    val systemValue: String,
    val growthRate: String
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LazyRowScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // dataset
    val mockInsights = remember {
        listOf(
            MetricInsight("m1", "Memory Usage", "240 MB", "-4.2%"),
            MetricInsight("m2", "Frame Velocity", "60 FPS", "+0.0%"),
            MetricInsight("m3", "Network RTT", "18 ms", "-12.5%"),
            MetricInsight("m4", "Thread Spawns", "32 Active", "+2.1%"),
            MetricInsight("m5", "Disk Cache", "4.8 GB", "+8.4%")
        )
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Horizontal Carousel")
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "System Telemetry Streams",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 16.dp)
            )

            //1. Core Lazy Row Implementation
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                // padding content bounds so that first/last items align perfectly with screen edges while scrolling
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    items = mockInsights,
                    key = { insight -> insight.id }
                ) { insight ->
                    CarouselCard(insight)
                }
            }

            Spacer(Modifier.height(8.dp))

            Card(
                modifier = Modifier.padding(horizontal = 16.dp).fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Text(
                    text = "Horizontal Carousels are ideal for scanning high-level metrics cleanly. Avoid embedding wide detailed data metrics inside horizontal loops to ensure easy user readability.",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun CarouselCard(
    insight: MetricInsight,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(160.dp) //Crucial -> give carousel row children fixed horizontal widths
            .wrapContentHeight(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Analytics,
                contentDescription = "Analytics",
                tint = MaterialTheme.colorScheme.primary
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = insight.title,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.outline
            )
            Text(
                text = insight.systemValue,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )
            Spacer(Modifier.height(4.dp))
            Text(
                text = insight.growthRate,
                style = MaterialTheme.typography.bodySmall,
                color = if (insight.growthRate.startsWith("-")) Color(0xFF4CAF50) else MaterialTheme.colorScheme.error,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}