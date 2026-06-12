package com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackHandlerScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var hasUnsavedChanges by remember { mutableStateOf(false) }
    var showWarningDialog by remember { mutableStateOf(false) }

    // 1. Back Handler Composable
    // 'enabled' controls whether this block intercepts the system back event.
    // If true, the system gesture is trapped here. If false, it falls back to the parent NavHost.
    BackHandler(enabled = hasUnsavedChanges) {
        showWarningDialog = true
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Back Press Interception"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Form Submission Status",
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.outline
                )

                Text(
                    text = if (hasUnsavedChanges) "Unsaved Changes active" else "Form saved/Clean State",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (hasUnsavedChanges) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                )

                Text(
                    text = "Toggle the state below to see how the system back gesture behaves. When 'unsaved changes' is active, swiping back will trigger an alert dialog instead of leaving the screen.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(
                    onClick = {
                        hasUnsavedChanges = !hasUnsavedChanges
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = if (hasUnsavedChanges) MaterialTheme.colorScheme.error else MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(
                        text = if (hasUnsavedChanges) "Mark Form as Clean" else "Simulate Modifying Form Data"
                    )
                }
            }
        }

        // 2. Unsaved Changes Alert Dialog
        if (showWarningDialog) {
            AlertDialog(
                onDismissRequest = {
                    showWarningDialog = false
                },
                title = {
                    Text(
                        text = "Discard Unsaved changes?"
                    )
                },
                text = {
                    Text(
                        text = "You have uncommitted modifications. Navigating away will lose all typed data permanently."
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            showWarningDialog = false
                            hasUnsavedChanges = false //Disables BackHandler override
                            onBack()
                        }
                    ) {
                        Text(
                            text = "Discard",
                            color = MaterialTheme.colorScheme.error
                        )
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = {
                            showWarningDialog = false
                        }
                    ) {
                        Text(
                            text = "Keep Editing"
                        )
                    }
                }
            )
        }
    }
}