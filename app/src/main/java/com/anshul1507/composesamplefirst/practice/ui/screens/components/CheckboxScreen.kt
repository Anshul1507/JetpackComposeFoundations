package com.anshul1507.composesamplefirst.practice.ui.screens.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TriStateCheckbox
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.state.ToggleableState
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CheckboxScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // 1. Core State: Individual child binary states
    var childOneChecked by remember { mutableStateOf(false) }
    var childTwoChecked by remember { mutableStateOf(false) }

    // 2. Derive parent State: based on child states
    val parentToggleState = remember(childOneChecked, childTwoChecked) {
        if (childOneChecked && childTwoChecked) {
            ToggleableState.On
        } else if (!childOneChecked && !childTwoChecked) {
            ToggleableState.Off
        } else {
            ToggleableState.Indeterminate
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Multi-State Checkboxes"
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
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            Text(
                text = "Hierarchical Bulk Selection",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f)
                )
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    //PARENT ROW USING TRI-STATE CHECKBOX
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                // Bulk selection logic when parent is tapped
                                val targetState = parentToggleState != ToggleableState.On
                                childOneChecked = targetState
                                childTwoChecked = targetState
                            }
                            .padding(vertical = 8.dp)
                    ) {
                        TriStateCheckbox(
                            state = parentToggleState,
                            onClick = null // Handled on parent row click
                        )
                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )
                        Text(
                            text = "Select all modules",
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    HorizontalDivider(
                        modifier = Modifier.padding(vertical = 8.dp)
                    )

                    // CHILD ROW 1
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp)
                            .padding(vertical = 8.dp)
                            .clickable {
                                childOneChecked = !childOneChecked
                            }
                    ) {
                        Checkbox(
                            checked = childOneChecked,
                            onCheckedChange = null // parent row handling click
                        )
                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )
                        Text(
                            text = "Checkbox A",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // CHILD ROW 2
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 24.dp)
                            .padding(vertical = 8.dp)
                            .clickable {
                                childTwoChecked = !childTwoChecked
                            }
                    ) {
                        Checkbox(
                            checked = childTwoChecked,
                            onCheckedChange = null // parent row handling click
                        )
                        Spacer(
                            modifier = Modifier.width(12.dp)
                        )
                        Text(
                            text = "Checkbox B",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }
        }
    }
}