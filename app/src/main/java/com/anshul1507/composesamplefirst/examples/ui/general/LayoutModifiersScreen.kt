package com.anshul1507.composesamplefirst.examples.ui.general

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LayoutModifiersScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Layout Modifiers"
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
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            // 1. The padding Order-of-execution trick (Simulating Margins)
            ModifierSection(
                title = "1. Padding vs Margin (Order Matters)",
                description = "Compose doesn't have a 'margin' modifier. We simulate it by placing a background between two padding modifiers."
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp) // 1st padding acts as OUTER MARGIN
                        .background(MaterialTheme.colorScheme.primary)
                        .padding(16.dp) // 2nd padding acts as INNER PADDING
                ) {
                    Text(
                        text = "Outer Margin: 24dp | Inner Padding: 16dp",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // 2. Layout Offset
            ModifierSection(
                title = "2. Layout Offset (X & Y Alignment)",
                description = "Offsets shift content visually without altering the measured space allocated to it by the parent layout."
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.surfaceVariant)
                        .padding(16.dp)
                ) {
                    Text(
                        text = "Base Layout Position",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )

                    Box(
                        modifier = Modifier
                            .offset(x = 40.dp, y = 10.dp) //Shifts the element 40dp right and 10dp down
                            .background(MaterialTheme.colorScheme.error)
                            .padding(8.dp)
                    ) {
                        Text(
                            text = "Shifted via .offset()",
                            color = MaterialTheme.colorScheme.onError,
                            style = MaterialTheme.typography.labelLarge
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

            // 3. Enforcing a Fixed Aspect Ratio
            ModifierSection(
                title = "3. Fixed Aspect Ratio (16:9 Cinema)",
                description = "Forces the width-to-height ratio to stay consistent regardless of device screen size width."
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                        .aspectRatio(16f/9f)
                        .background(MaterialTheme.colorScheme.tertiary),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "16:9 Aspect Ratio Container",
                        color = MaterialTheme.colorScheme.onTertiary,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
fun ModifierSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
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
        Spacer(modifier = Modifier.height(12.dp))
        content()
    }
}