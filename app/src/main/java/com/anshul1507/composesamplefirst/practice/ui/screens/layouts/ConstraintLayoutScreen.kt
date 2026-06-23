package com.anshul1507.composesamplefirst.practice.ui.screens.layouts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConstraintLayoutScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Constraint Layout Primitives")
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Flat UI Architecture Card",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            // 1. Instantiating the Flat Constraint Layout Container
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                    .padding(16.dp)
            ) {
                // creating the reference anchors for each child view node
                val (avatar, title, subtitle, divider, actioButton) = createRefs()

                // Establishing a fixed vertical guideline at 15% of the card width
                val startGuideline = createGuidelineFromStart(0.15f)

                // 2. Anchor the avatar profile circle using Guidelines and the top bounds
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.primary)
                        .constrainAs(avatar) {
                            top.linkTo(parent.top)
                            // Align the center of the avatar directly on top of our guideline marker axis
                            start.linkTo(startGuideline)
                            end.linkTo(startGuideline)
                        }
                )

                // 3. Anchor the title text to the right of the avatar with bias alignment rules
                Text(
                    text = "Android System pipeline",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.constrainAs(title) {
                        start.linkTo(avatar.end, margin = 16.dp)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 4.dp)
                        width = Dimension.fillToConstraints
                        horizontalBias = 0f
                    }
                )

                // 4. Anchor the subtitle text directly beneath the main title block
                Text(
                    text = "Telemetry node distribution active.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.constrainAs(subtitle) {
                        start.linkTo(title.start)
                        end.linkTo(parent.end)
                        top.linkTo(title.bottom, margin = 4.dp)
                        width = Dimension.fillToConstraints
                        horizontalBias = 0f
                    }
                )

                // Creating a horizontal barrier beneath BOTH the avatar and subtitle blocks
                val cardTopContentBarrier = createBottomBarrier(avatar, subtitle, margin = 16.dp)

                // Divider
                HorizontalDivider(
                    modifier = Modifier.constrainAs(divider) {
                        top.linkTo(cardTopContentBarrier)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                )

                // Position a button  under the divider and to the right 
                Button(
                    onClick = { },
                    modifier = Modifier.constrainAs(actioButton) {
                        top.linkTo(divider.bottom, margin = 12.dp)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    }
                ) {
                    Text("Click Me!")
                }

            }
        }
    }
}