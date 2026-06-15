package com.anshul1507.composesamplefirst.practice.ui.screens.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SnackbarActionScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // Stores the operational queue for message popups
    val snackbarHostState = remember { SnackbarHostState() }

    // we need a coroutine scope b/c showing a snackbar is a suspending async function
    val coroutineScope = rememberCoroutineScope()

    var operationStatus by remember { mutableStateOf("Waiting for user action...") }
    Scaffold(
        snackbarHost = {
            SnackbarHost(
                hostState = snackbarHostState
            )
        },
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dynamic Snackbars"
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
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                Text(
                    text = "System Console Monitor",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.outline
                )
                Text(
                    text = operationStatus,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )

                Spacer(
                    modifier = Modifier.height(16.dp)
                )

                // Action 1: Simple Message Alert
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Database snapshot saved successfully.",
                                withDismissAction = true // Adds the native close 'X' button helper
                            )
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Trigger Simple Auto-Dismiss Snackbar"
                    )
                }

                // Action 2: Complete Dynamic Interaction Loop
                Button(
                    onClick = {
                        operationStatus = "Processing data mutation layer..."

                        coroutineScope.launch {
                            // showSnackbar suspends processing and waits for the user to tap or timeout
                            val result = snackbarHostState.showSnackbar(
                                message = "File deleted permanently.",
                                actionLabel = "Undo Action",
                                duration = SnackbarDuration.Short
                            )

                            // Inspecting the exact feedback state returned by the coroutine block
                            operationStatus = when (result) {
                                SnackbarResult.ActionPerformed -> {
                                    "Rollback executed! File restoration complete."
                                }
                                SnackbarResult.Dismissed -> {
                                    "Notification timed out. Changes commited."
                                }
                            }
                        }
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.error
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Trigger Dangerous Event with Action"
                    )
                }
            }
        }
    }
}