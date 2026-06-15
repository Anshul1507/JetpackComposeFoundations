package com.anshul1507.composesamplefirst.practice.ui.screens.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.window.Dialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DialogsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var showStandardDialog by remember { mutableStateOf(false) }
    var showCustomModal by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Dialogs & Modals"
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
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showStandardDialog = true
                }
            ) {
                Text("Open Standard Alert Dialog")
            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    showCustomModal = true
                }
            ) {
                Text("Open Custom Content Modal")
            }

            // 1. Standard Alert Dialog
            if (showStandardDialog) {
                AlertDialog(
                    title = {
                        Text("Destructive Action Warning")
                    },
                    text = {
                        Text("Are you sure you want to go ahead with the flow?")
                    },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Warning,
                            contentDescription = null,
                            tint = MaterialTheme.colorScheme.error
                        )
                    },
                    onDismissRequest = { //Runs when user taps outside or presses system back
                        showStandardDialog = false
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                //Perform business logic here
                                showStandardDialog = false
                            }
                        ) {
                            Text("Confirm", color = MaterialTheme.colorScheme.error, fontWeight = FontWeight.Bold)
                        }
                    },
                    dismissButton = {
                        TextButton(
                            onClick = {
                                showStandardDialog = false
                            }
                        ) {
                            Text("Cancel")
                        }
                    }
                )
            }

            // 2. Custom Layout Dialog
            if (showCustomModal) {
                Dialog(
                    onDismissRequest = {
                        showCustomModal = false
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(24.dp))
                            .padding(24.dp)
                    ) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = "Premium Feature Unlocked",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold
                            )

                            CircularProgressIndicator(
                                progress = {
                                    0.75f
                                },
                                color = MaterialTheme.colorScheme.primary
                            )

                            Text(
                                text = "You have unlocked Module 3 structural UI component. Continue your progress.",
                                style = MaterialTheme.typography.bodyMedium
                            )

                            Button(
                                onClick = {
                                    showCustomModal = false
                                },
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text("Awesome, Let's Continue")
                            }
                        }
                    }
                }
            }

        }
    }
}