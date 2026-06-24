package com.anshul1507.composesamplefirst.practice.ui.screens.customDrawing

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
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
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Canvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeasuringScaleScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val minWeight = 30
    val maxWeight = 150

    // pixel space distribution between adjacent ruler line ticks
    val tickSpacePx = 24f

    // tracks the active pixel offset position modified by drag interactions
    var scrollOffsetPx by remember { mutableStateOf(0f) }

    val baseCenter = (minWeight + maxWeight) / 2
    val maxScrollOffset = (baseCenter - minWeight) * tickSpacePx
    val minScrollOffset = (baseCenter - maxWeight) * tickSpacePx

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Interactive Measuring Scale")
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
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            val centerValue = remember(scrollOffsetPx) {
                val baseCenter = (minWeight + maxWeight) / 2f
                val valueDelta = -scrollOffsetPx / tickSpacePx
                (baseCenter + valueDelta).coerceIn(minWeight.toFloat(), maxWeight.toFloat())
            }

            Text(
                text = "${"%.1f".format(centerValue)} kg",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(48.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                    .draggable(
                        orientation = Orientation.Horizontal,
                        state = rememberDraggableState { dataPixels ->
                            scrollOffsetPx = (scrollOffsetPx + dataPixels).coerceIn(
                                minimumValue = minScrollOffset,
                                maximumValue = maxScrollOffset
                            )
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                val lineColor = MaterialTheme.colorScheme.onSurfaceVariant
                val indicatorColor = MaterialTheme.colorScheme.error

                Canvas(modifier = Modifier.fillMaxSize()) {
                    val canvasWidth = size.width
                    val canvasHeight = size.height
                    val midCanvasX = canvasWidth / 2f

                    // Native paint instance for rendering numerical labels via typography engines
                    val textPaint = android.graphics.Paint().apply {
                        color = lineColor.hashCode()
                        textSize = 14.sp.toPx()
                        textAlign = android.graphics.Paint.Align.CENTER
                        isAntiAlias = true
                    }

                    // 5. Draw the entire range of calibration ticks dynamically
                    for (i in minWeight..maxWeight) {
                        val baseCenter = (minWeight + maxWeight) / 2f

                        // Calculate where each individual tick line should sit along the horizontal axis
                        val rawLineX = midCanvasX + (i - baseCenter) * tickSpacePx + scrollOffsetPx

                        // Optimization: Skip rendering entirely if the calculated position sits outside the viewport bounds
                        if (rawLineX < 0 || rawLineX > canvasWidth) continue

                        // Differentiate visual heights for major (5s/10s) vs minor tick intervals
                        val isMajorTick = i % 5 == 0
                        val tickLength = if (isMajorTick) 50.dp.toPx() else 25.dp.toPx()
                        val tickStroke = if (isMajorTick) 3.dp.toPx() else 1.5.dp.toPx()

                        // Draw the vertical tick line marker downwards from the top edge
                        drawLine(
                            color = lineColor.copy(alpha = if (isMajorTick) 0.8f else 0.4f),
                            start = Offset(x = rawLineX, y = 0f),
                            end = Offset(x = rawLineX, y = tickLength),
                            strokeWidth = tickStroke
                        )

                        // If it's a major increment milestone, paint the numeric value directly beneath it
                        if (isMajorTick) {
                            drawContext.canvas.nativeCanvas.drawText(
                                i.toString(),
                                rawLineX,
                                tickLength + 24.dp.toPx(),
                                textPaint
                            )
                        }
                    }

                    // 6. Draw the central reference pin indicator overlay
                    drawLine(
                        color = indicatorColor,
                        start = Offset(x = midCanvasX, y = 0f),
                        end = Offset(x = midCanvasX, y = 70.dp.toPx()),
                        strokeWidth = 4.dp.toPx()
                    )
                }
            }

            Spacer(Modifier.height(16.dp))

            Text(
                text = "Drag horizontally to adjust calibration values",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
            )

        }
    }
}