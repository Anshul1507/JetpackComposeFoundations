package com.anshul1507.composesamplefirst.practice.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SliderScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // State wrappers for sliders
    var continuousValue by remember { mutableFloatStateOf(40f) }
    var discreteValue by remember { mutableFloatStateOf(3f) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Sliders (Continuous & Discrete)"
                    )
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {

            // 1. Continuous Slider (Infinite values b/w constraints)
            SliderSection(
                title = "1. Continuous Slider (Volume / Brightness)",
                description = "Transitions smoothly without snapping. Useful for fine-grained adjustments."
            ) {
                Text(
                    text = "Current value: ${continuousValue.roundToInt()}%",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Slider(
                    value = continuousValue,
                    onValueChange = {
                        continuousValue = it
                    },
                    valueRange = 0f..100f,
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.primary,
                        activeTrackColor = MaterialTheme.colorScheme.primary
                    )
                )
            }

            // 2. Discrete Slider (Snapped to steps/ticks)
            SliderSection(
                title = "2. Discrete Slider (Quantized Steps)",
                description = "Splits the range mathematically via the 'steps' parameter. Automatically paints tick indicators along the track."
            ) {
                Text(
                    text = "Selected Star Rating: ${discreteValue.roundToInt()} stars",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.tertiary
                )

                Slider(
                    value = discreteValue,
                    onValueChange = {
                        discreteValue = it
                    },
                    valueRange = 1f..5f,
                    steps = 3, // this creates 3 interior markers b/w 1f and 5f (allocating 5 total discrete points: 1,2,3,4,5)
                    colors = SliderDefaults.colors(
                        thumbColor = MaterialTheme.colorScheme.tertiary,
                        activeTrackColor = MaterialTheme.colorScheme.tertiary,
                        activeTickColor = MaterialTheme.colorScheme.onTertiary,
                        inactiveTickColor = MaterialTheme.colorScheme.tertiary.copy(alpha = 0.4f)
                    )
                )
            }
        }

    }
}

@Composable
fun SliderSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        content()
    }
}