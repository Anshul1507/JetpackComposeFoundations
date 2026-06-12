package com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SideEffectsScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val coroutineScope =
        rememberCoroutineScope() //Used for launching coroutines inside UI event callbacks (clicks)

    var isLoadingData by remember { mutableStateOf(true) }
    var networkDataLoaded by remember { mutableStateOf("No data loaded yet.") }

    // Runs async when entering the composition tree.
    // Passing 'Unit' as a key ensures this block runs EXACTLY ONCE on screen entry, surviving recompositions.
    LaunchedEffect(key1 = Unit) {
        delay(3000)
        networkDataLoaded = "Successfully fetched 50+ items from API!"
        isLoadingData = false

        //Triggering an immediate follow-up snackbar side-effect alert natively
        snackbarHostState.showSnackbar("Data sync completed successfully.")
    }

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
                        text = "Side-Effects & Coroutines"
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
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                // Displaying state driven by our LaunchedEffect block
                if (isLoadingData) {
                    CircularProgressIndicator()
                    Text(
                        text = "LaunchedEffect simulating network fetch..."
                    )
                } else {
                    Text(
                        text = networkDataLoaded,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                HorizontalDivider(
                    modifier = Modifier.padding(vertical = 8.dp)
                )

                Text(
                    text = "Need to trigger an async event on a manual button press? Never use LaunchedEffect for that. Use rememberCoroutineScope instead.",
                    style = MaterialTheme.typography.bodyMedium
                )

                Button(
                    onClick = {
                        // Launching a coroutine safely inside a standard non-composable click callback
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(
                                message = "Manual snackbar execution triggered!",
                                actionLabel = "Dismiss"
                            )
                        }
                    }
                ) {
                    Text(
                        text = "Trigger Manual Coroutine Side-Effect"
                    )
                }
            }
        }
    }
}