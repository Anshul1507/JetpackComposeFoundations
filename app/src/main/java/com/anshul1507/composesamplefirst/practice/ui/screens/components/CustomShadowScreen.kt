package com.anshul1507.composesamplefirst.practice.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomShadowScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Shadows & Elevation"
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
            verticalArrangement = Arrangement.spacedBy(32.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // 1. Standard Material 3 Tonal Elevation
            ShadowSection(
                title = "1. Material 3 Tonal Elevation",
                description = "M3 uses surface color tinting to indicate depth. Higher elevation = stronger brand color overlay tint."
            ) {
                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(12.dp),
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation = 8.dp, //Automatically overlays primary color based on elevation level
                    shadowElevation = 2.dp // subtle ambient drop shadow
                ) {
                    Box(
                        modifier = Modifier.padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("Tonal Elevation Surface", fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            // 2. Custom Colored Glow Drop Shadow
            ShadowSection(
                title = "2. Advanced Custom Glow Shadow",
                description = "Using a custom native canvas painting modifier to render blurred, colored shadows with directional offsets."
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .customShadow(
                            color = Color(0xFF6200EE),
                            alpha = 0.25f,
                            borderRadius = 16.dp,
                            shadowRadius = 12.dp,
                            offsetX = 0.dp,
                            offsetY = 8.dp
                        )
                        .background(
                            MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(16.dp)
                        )
                        .padding(24.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Custom Purple Glow Shadow",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF6200EE)
                    )
                }
            }
        }
    }
}

/**
 * Reusable Custom Shadow Modifier Extension
 * Leverages the native Android framework BlurMaskFilter via drawBehind.
 */
fun Modifier.customShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 8.dp,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp
) = this.drawBehind {
    drawIntoCanvas { canvas ->
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()

        // Resolve transparent shadow color tokens
        val shadowColor = color.copy(alpha = alpha).toArgb()
        frameworkPaint.color = shadowColor

        // Inject software layer blur parameters to get soft skeuomorphic drop edges
        if (shadowRadius.toPx() > 0f) {
            frameworkPaint.setShadowLayer(
                shadowRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                shadowColor
            )
        }

        val width = size.width
        val height = size.height

        // Draw the precise boundary matching the component shape wrapper
        canvas.drawRoundRect(
            left = 0f,
            top = 0f,
            right = width,
            bottom = height,
            radiusX = borderRadius.toPx(),
            radiusY = borderRadius.toPx(),
            paint = paint
        )
    }
}

@Composable
fun ShadowSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
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