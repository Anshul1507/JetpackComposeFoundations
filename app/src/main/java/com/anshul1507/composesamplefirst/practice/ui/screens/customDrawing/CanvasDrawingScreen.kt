package com.anshul1507.composesamplefirst.practice.ui.screens.customDrawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CanvasDrawingScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Canvas & Geometry")
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
            // 1. Custom Progress Donut Chart via sweeps
            DrawingSection(
                title = "1. Analytical Ring & Gradient Sweep",
                desc = "Renders a segmented data chart ring using drawArc combined with a multi-color brush gradient wrapper."
            ) {
                val gradientColors = listOf(Color(0xFF6200EE), Color(0xFF03DAC6), Color(0xFF6200EE))

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                ) {
                    val canvasSize = size
                    val strokeWidth = 24.dp.toPx()
                    val diameter = minOf(canvasSize.width, canvasSize.height) * 0.7f

                    // Center the bounding box frame map coordinates completely within the canvas
                    val topLeftOffset = Offset(
                        x = (canvasSize.width - diameter) / 2f,
                        y = (canvasSize.height - diameter) / 2f
                    )
                    val arcSize = Size(diameter, diameter)

                    drawCircle(
                        color = Color.LightGray.copy(alpha = 0.3f),
                        radius = diameter / 2f,
                        center = center,
                        style = Stroke(width = strokeWidth)
                    )

                    drawArc(
                        brush = Brush.sweepGradient(colors = gradientColors, center = center),
                        startAngle = -90f,
                        sweepAngle = 270f,
                        useCenter = false,
                        topLeft = topLeftOffset,
                        size = arcSize,
                        style = Stroke(width = strokeWidth)
                    )
                }
            }

            // 2. Vector Shape Paths & Geometric Polygons
            DrawingSection(
                title = "2. Explicit Vector Path Graphics",
                desc = "Constructing a complex visual polygon graph mapping coordinates mathematically via drawing Path vectors."
            ) {
                val pathStrokeColor = MaterialTheme.colorScheme.primary

                Canvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp)
                        .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
                ) {
                    val width = size.width
                    val height = size.height

                    // Instantiate a raw geometric line sequence blueprint mapping path trace tracks
                    val vectorPath = Path().apply {
                        moveTo(
                            width * 0.1f,
                            height * 0.8f
                        )      // Step A: Bottom-Left origin coordinate
                        lineTo(width * 0.35f, height * 0.2f)     // Step B: Top peak vector point
                        lineTo(
                            width * 0.6f,
                            height * 0.6f
                        )      // Step C: Mid-Valley coordinate anchor
                        lineTo(
                            width * 0.9f,
                            height * 0.1f
                        )      // Step D: High-Right peak vector point
                    }

                    // Render the mathematical track blueprint onto the hardware frame canvas layer
                    drawPath(
                        path = vectorPath,
                        color = pathStrokeColor,
                        style = Stroke(
                            width = 4.dp.toPx(),
                            // Gives the path vertices clean, rounded connecting joints
                            join = androidx.compose.ui.graphics.StrokeJoin.Round
                        )
                    )

                    // Draw localized marker accent dots precisely at each peak vector cross-section coordinate
                    drawCircle(
                        color = Color.Red,
                        radius = 6.dp.toPx(),
                        center = Offset(width * 0.1f, height * 0.8f)
                    )
                    drawCircle(
                        color = Color.Red,
                        radius = 6.dp.toPx(),
                        center = Offset(width * 0.35f, height * 0.2f)
                    )
                    drawCircle(
                        color = Color.Red,
                        radius = 6.dp.toPx(),
                        center = Offset(width * 0.6f, height * 0.6f)
                    )
                    drawCircle(
                        color = Color.Red,
                        radius = 6.dp.toPx(),
                        center = Offset(width * 0.9f, height * 0.1f)
                    )
                }
            }
        }
    }
}

@Composable
fun DrawingSection(
    title: String,
    desc: String,
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
            text = desc,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        content()
    }
}