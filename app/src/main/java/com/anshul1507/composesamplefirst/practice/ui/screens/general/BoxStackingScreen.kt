package com.anshul1507.composesamplefirst.practice.ui.screens.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxStackingScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "FrameLayout Stacking Via Box")
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
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            // 1. Basic component Layering & Alignment
            StackSection(
                title = "1. Component Layering & Alignments",
                description = "Placing elements at absolute layout coordinates within a parent box boundary."
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surfaceVariant,
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(12.dp)
                ) {
                    //Item A : Ground Floor (Top Left by default)
                    Text(
                        text = "Top-Start Layer",
                        modifier = Modifier.align(Alignment.TopStart),
                        style = MaterialTheme.typography.labelMedium
                    )

                    //Item B: Center Layer (Loading Indicator)
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )

                    //Item C: Bottom Right layer
                    Button(
                        onClick = {},
                        modifier = Modifier.align(Alignment.BottomEnd)
                    ) {
                        Text("Click me!")
                    }
                }
            }

            // 2. E-Commerce Product Image with overlaid wishlist badge
            StackSection(
                title = "2. Production Use Case: Floating Action Badges",
                description = "Overlaying a heart/wishlist button cleanly over a background media component."
            ) {
                Box(
                    modifier = Modifier
                        .size(
                            width = 200.dp, height = 150.dp
                        )
                        .background(
                            color = MaterialTheme.colorScheme.outlineVariant,
                            shape = RoundedCornerShape(8.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "[Product Image Container]",
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.bodySmall
                    )
                    IconButton(
                        onClick = {},
                        modifier = Modifier
                            .padding(8.dp)
                            .align(Alignment.TopEnd)
                            .background(Color.White, shape = CircleShape)
                            .size(36.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Favorite,
                            contentDescription = "Favorite",
                            tint = Color.Red,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }

            // 3. Modifying Custom Dimension Pass with matchParentSize
            StackSection(
                title = "3. Smart Bounds with matchParentSize",
                description = "Forces the overlay layer to match the parent constraints AFTER the primary child establishes the core size dimensions."
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            color = MaterialTheme.colorScheme.tertiaryContainer,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(12.dp)
                ) {
                    Text(
                        text = "This single text row is long and establishes the measurement size of the parent layout block.",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(bottom = 24.dp)
                    )

                    Box(
                        modifier = Modifier
                            //.fillMaxSize() //this will take up the max size available of the parent
                            .matchParentSize() //this will take the size of the resolved size of teh parent container
                            .background(
                                color = MaterialTheme.colorScheme.primary.copy(alpha = 0.1f),
                                shape = RoundedCornerShape(4.dp)
                            )
                    )
                }
            }

        }
    }
}

@Composable
fun StackSection(
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