package com.anshul1507.composesamplefirst.practice.ui.screens.general

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimationBasicsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Basic Animations"
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
            // 1. Infinite Loading Rotation Loop
            AnimationSection(
                title = "1. Infinite Rotation Loop",
                description = "Uses rememberInfiniteTransition to continuously cycle a float state from 0* to 360*."
            ) {
                val infiniteTransition = rememberInfiniteTransition(label = "RotationTransition")

                val rotatingAngle by infiniteTransition.animateFloat(
                    initialValue = 0f,
                    targetValue = 360f,
                    animationSpec = infiniteRepeatable(
                        animation = tween(durationMillis = 1500, easing = LinearEasing)
                    ),
                    label = "DegreeValue"
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Syncing",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier
                            .size(48.dp)
                            .rotate(rotatingAngle)
                    )

                    Text(
                        text = "Synchronizing background data...",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }

            }

            // 2. One-Shot color transition state
            AnimationSection(
                title = "2. State Driven Color Transition",
                description = "Uses animateColorAsState to interpolate smoothly whenver a layout toggle occurs."
            ) {
                var isPremiumActivated by remember { mutableStateOf(false) }

                // Compose monitors this state; when it flips, it smoothly recalculates hex values over time
                val animatedBgColor by animateColorAsState(
                    targetValue = if (isPremiumActivated) Color(0xFF10B981) else MaterialTheme.colorScheme.secondaryContainer,
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
                    label = "ColorTransition"
                )
                val animatedContentColor by animateColorAsState(
                    targetValue = if (isPremiumActivated) Color.White else MaterialTheme.colorScheme.onSecondaryContainer,
                    animationSpec = tween(durationMillis = 500),
                    label = "TextColorTransition"
                )

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = animatedBgColor,
                            shape = RoundedCornerShape(12.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = if (isPremiumActivated) "Premium Account" else "Standard Account",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = animatedContentColor
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { isPremiumActivated = !isPremiumActivated },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPremiumActivated) Color.Black else MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = if (isPremiumActivated) "Deactivate" else "Activate")
                    }
                }
            }
        }
    }
}

@Composable
fun AnimationSection(
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