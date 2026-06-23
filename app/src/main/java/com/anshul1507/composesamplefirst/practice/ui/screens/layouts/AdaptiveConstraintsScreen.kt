package com.anshul1507.composesamplefirst.practice.ui.screens.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdaptiveConstraintsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Adaptive Decoupled Layouts")
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
        // 1. Core Wrapper - Reading runtime viewport metrics
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            // Threshold check - if the screen width constraints are wide (landscape mode / tablet)

            val width = maxWidth
            val isWideScreen = width > 600.dp
            // Select the optimal decoupled architecture constraints profile dynamically
            val activeConstraints = if (isWideScreen) {
                buildLandscapeConstraints()
            } else {
                buildPortraitConstraints()
            }

            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Text(
                    text = "Adaptive Form (Current width: ${width})",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )

                ConstraintLayout(
                    constraintSet = activeConstraints,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(12.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.3f))
                        .padding(16.dp)
                ) {
                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text("TextField A")
                        },
                        modifier = Modifier.layoutId("inputFieldA")
                    )

                    OutlinedTextField(
                        value = "",
                        onValueChange = {},
                        label = {
                            Text("TextField B")
                        },
                        modifier = Modifier.layoutId("inputFieldB")
                    )

                    Button(
                        onClick = {},
                        modifier = Modifier.layoutId("submitButton")
                    ) {
                        Text("Submit Button")
                    }
                }
            }
        }
    }
}

private fun buildPortraitConstraints(): ConstraintSet {
    return ConstraintSet {
        val inputFieldA = createRefFor("inputFieldA")
        val inputFieldB = createRefFor("inputFieldB")
        val submitButton = createRefFor("submitButton")

        constrain(inputFieldA) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(inputFieldB) {
            top.linkTo(inputFieldA.bottom, margin = 12.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(submitButton) {
            top.linkTo(inputFieldB.bottom, margin = 16.dp)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }
    }
}

private fun buildLandscapeConstraints(): ConstraintSet {
    return ConstraintSet {
        val inputFieldA = createRefFor("inputFieldA")
        val inputFieldB = createRefFor("inputFieldB")
        val submitButton = createRefFor("submitButton")

        constrain(inputFieldA) {
            top.linkTo(parent.top)
            start.linkTo(parent.start)
            end.linkTo(inputFieldB.start, margin = 8.dp)
            width = Dimension.percent(0.45f)
        }

        constrain(inputFieldB) {
            top.linkTo(parent.top)
            start.linkTo(inputFieldA.end, margin = 8.dp)
            end.linkTo(parent.end)
            width = Dimension.percent(0.45f)
        }

        constrain(submitButton) {
            top.linkTo(inputFieldA.bottom, margin = 16.dp)
            end.linkTo(parent.end)
            width = Dimension.wrapContent
        }
    }
}