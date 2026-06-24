package com.anshul1507.composesamplefirst.practice.ui.screens.architecture

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypeSafeNavigationScreen(
    currentNodeId: String,
    currentClearance: Int,
    onNavigationWithArgs: (nodeId: String, clearance: Int) -> Unit,
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var inputNode by remember { mutableStateOf("NODE-779X") }
    var inputClearance by remember { mutableStateOf(4) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text("Type Safe Navigation")
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

            // Unpacking and showcasing incoming args
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.primaryContainer.copy(alpha = 0.4f))
                    .padding(16.dp)
            ) {
                Text(
                    text = "Incoming Args Resolved: ",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Spacer(Modifier.height(8.dp))
                Text(
                    text = "Node ID: $currentNodeId",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Security Level: Class $currentClearance",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            // Outbound params compiler from panel
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Trigger Outbound Params",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold
                )
                OutlinedTextField(
                    value = inputNode,
                    onValueChange = {
                        inputNode = it
                    },
                    label = {
                        Text("Custom Node")
                    },
                    modifier = Modifier.fillMaxWidth()
                )

                Text(
                    text = "Target Clearance Level: $inputClearance",
                    style = MaterialTheme.typography.labelMedium
                )
                Slider(
                    value = inputClearance.toFloat(),
                    onValueChange = {
                        inputClearance = it.toInt()
                    },
                    valueRange = 1f..5f,
                    steps = 3
                )

                Button(
                    onClick = {
                        onNavigationWithArgs(inputNode, inputClearance)
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Navigate Type-Safe")
                }
            }
        }
    }
}