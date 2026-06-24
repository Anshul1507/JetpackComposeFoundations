package com.anshul1507.composesamplefirst.practice.ui.screens.customDrawing

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.forEachGesture
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

data class LineStroke(
    val points: List<Offset>,
    val color: Color = Color(0xFF6200EE),
    val strokeWidth: Float = 8f
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PaintCanvasScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val historicStrokes = remember { mutableStateListOf<LineStroke>() }
    var currentStrokePoints = remember { mutableStateListOf<Offset>() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Paint Pad Canvas")
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) { }
        Text(
            text = "Draw freehand below with your finger",
            style = MaterialTheme.typography.titleMedium
        )

        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(12.dp))
                .background(Color.White)
                .pointerInput(Unit) {
                    forEachGesture {
                        awaitPointerEventScope {
                            // wait for initial single finger down touch interaction
                            val downPointer = awaitFirstDown()
                            currentStrokePoints.add(downPointer.position)

                            // continuously track coordinate stream until finger pulls up
                            while (true) {
                                val event = awaitPointerEvent()
                                val anyChangesActive = event.changes.any { it.pressed }

                                if (!anyChangesActive) break // Loop breaks when finger is lifted

                                val activeChange = event.changes.first()
                                currentStrokePoints.add(activeChange.position)

                                activeChange.consume()
                            }

                            if (currentStrokePoints.isNotEmpty()) {
                                historicStrokes.add(LineStroke(points = currentStrokePoints.toList()))
                                currentStrokePoints.clear()
                            }
                        }
                    }
                }
        ) {
            // drawing historic complete lines onto screen layer
            historicStrokes.forEach { stroke ->
                if (stroke.points.size > 1) {
                    val path = Path().apply {
                        moveTo(stroke.points.first().x, stroke.points.first().y)
                        for (i in 1 until stroke.points.size) {
                            lineTo(stroke.points[i].x, stroke.points[i].y)
                        }
                    }
                    drawPath(
                        path = path,
                        color = stroke.color,
                        style = Stroke(
                            width = stroke.strokeWidth,
                            cap = StrokeCap.Round,
                            join = StrokeJoin.Round
                        )
                    )
                }
            }

            //drawing active in-flight line render segments
            if (currentStrokePoints.size > 1) {
                val activePath = Path().apply {
                    moveTo(currentStrokePoints.first().x, currentStrokePoints.first().y)
                    for (i in 1 until currentStrokePoints.size) {
                        lineTo(currentStrokePoints[i].x, currentStrokePoints[i].y)
                    }
                }
                drawPath(
                    path = activePath,
                    color = Color(0xFF6200EE),
                    style = Stroke(
                        width = 8f,
                        cap = StrokeCap.Round,
                        join = StrokeJoin.Round
                    )
                )
            }
        }
    }
}