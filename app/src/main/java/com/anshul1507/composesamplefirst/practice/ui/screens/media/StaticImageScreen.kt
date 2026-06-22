package com.anshul1507.composesamplefirst.practice.ui.screens.media

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material.icons.filled.Terminal
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.anshul1507.composesamplefirst.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StaticImageScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Static Images & Vectors")
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
            verticalArrangement = Arrangement.spacedBy(28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 1. Image Loading with custom shapes and borders
            ImageSection(
                title = "1. Shape Clipping & Borders",
                description = "Loading a local drawable PNG/JPEG, cropping it into a circular avatar profile, and applying a custom border stroke."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(24.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Circular avatar
                    Image(
                        painter = painterResource(R.drawable.baseline_account_circle_24),
                        contentDescription = "User",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(CircleShape)
                            .border(
                                BorderStroke(2.dp, MaterialTheme.colorScheme.primary),
                                CircleShape
                            )
                    )

                    // Rounded Corners Square Grid
                    Image(
                        painter = painterResource(R.drawable.baseline_account_circle_24),
                        contentDescription = "user profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(80.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .border(
                                BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
                                RoundedCornerShape(12.dp)
                            )
                    )
                }
            }

            // 2. Vector Graphics and Runtime Tinting
            ImageSection(
                title = "2. Vector Graphic Tinting",
                description = "Manipulating individual vector paths at runtime by applying unified Material Design color filters directly onto asset streams."
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(32.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Standard System Icon Vector
                    Icon(
                        imageVector = Icons.Default.Terminal,
                        contentDescription = "System terminal",
                        tint = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(48.dp)
                    )

                    // custom XML Vector Tinting using ColorFilter
                    Image(
                        imageVector = Icons.Default.Terminal,
                        contentDescription = "Custom brand icon",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.error), // runtime color overlay transformation
                        modifier = Modifier.size(48.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun ImageSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier.fillMaxWidth()) {
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
        Spacer(Modifier.height(16.dp))
        content()
    }
}