package com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateHoistingScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Stateful Parent:  Holds the source-of-truth state
    var selectedRole by remember { mutableStateOf("None Selected") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "State Hoisting Patterns"
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
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Active Choice: $selectedRole",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Text(
                text = "The cards below are completely stateless. They don't know if they are selected or what happens when clicked. They simply render parameters passed down and bubbles click intents up.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            // Reusing the hoisted component multiple times with different values
            SelectableRoleCard(
                roleName = "Android Engineer",
                isSelected = selectedRole == "Android Engineer",
                onRoleSelected = {
                    selectedRole = "Android Engineer"
                }
            )

            SelectableRoleCard(
                roleName = "Backend Architect",
                isSelected = selectedRole == "Backend Architect",
                onRoleSelected = {
                    selectedRole = "Backend Architect"
                }
            )

            SelectableRoleCard(
                roleName = "Product Designer",
                isSelected = selectedRole == "Product Designer",
                onRoleSelected = {
                    selectedRole = "Product Designer"
                }
            )
        }
    }
}

/**
 * Completely Stateless, Resuable Composable
 * It exposes exactly two parameters for state matching:
 * 1. The state value flowing DOWN (isSelected)
 * 2. An event lambda bubbling UP (onRoleSelected)
 */
@Composable
fun SelectableRoleCard(
    roleName: String,
    isSelected: Boolean,
    onRoleSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onRoleSelected, // click event back to the parent immediately
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                MaterialTheme.colorScheme.surface
            }
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.outlineVariant
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = roleName,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal
            )

            RadioButton(
                onClick = onRoleSelected,
                selected = isSelected
            )
        }
    }
}