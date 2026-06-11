package com.anshul1507.composesamplefirst.practice.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anshul1507.composesamplefirst.practice.data.FeatureCategory
import com.anshul1507.composesamplefirst.practice.data.FeatureExample
import com.anshul1507.composesamplefirst.practice.data.FeatureRegistry
import com.anshul1507.composesamplefirst.practice.navigation.ScreenRoute

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    onFeatureClick: (ScreenRoute) -> Unit, modifier: Modifier = Modifier
) {
    // Group items dynamically by their category enum
    val groupedFeatures: Map<FeatureCategory, List<FeatureExample>> =
        FeatureRegistry.items.groupBy { it.category }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Compose Practice", fontWeight = FontWeight.Bold
                    )
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onPrimaryContainer
                )
            )
        }, modifier = modifier
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            groupedFeatures.forEach { (category, features) ->

                //Sticky Header for the Category Section
                stickyHeader {
                    CategoryHeader(category.displayName)
                }

                //Feature List
                items(features) { feature ->
                    FeatureRow(
                        feature, onClick = {
                            onFeatureClick(feature.destination)
                        })
                    HorizontalDivider(
                        Modifier.padding(horizontal = 16.dp),
                        color = MaterialTheme.colorScheme.outlineVariant.copy(alpha = 0.5f)
                    )
                }
            }
        }

    }
}

@Composable
fun CategoryHeader(
    title: String, modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant)
            .padding(horizontal = 16.dp, vertical = 10.dp)
    ) {
        Text(
            text = title.uppercase(),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun FeatureRow(
    feature: FeatureExample, onClick: () -> Unit, modifier: Modifier = Modifier
) {
    Row(modifier = modifier
        .fillMaxWidth()
        .clickable { onClick() }
        .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically) {
        Column(Modifier.weight(1f)) {
            Text(
                feature.title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(
                Modifier.height(4.dp)
            )
            Text(
                feature.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
            contentDescription = "Navigate to Feature",
            tint = MaterialTheme.colorScheme.outline
        )
    }
}