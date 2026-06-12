package com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SaveableStateScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Process Death Survival"
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
            // 1. Volatile Input Field (Fails on Rotation)
            SaveableDemoSection(
                title = "1. Plain 'remember' Input (Volatile)",
                description = "Type text here and rotate your emulator (Ctrl + Left Arrow). The state will clear out completely because the composition tree is destroyed."
            ) {
                var volatileInput by remember { mutableStateOf("") }

                TextField(
                    value = volatileInput,
                    onValueChange = {
                        volatileInput = it
                    },
                    placeholder = {
                        Text("Lost on rotation...")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // 2. Persistent Input Field (Survives Configuration Changes & Process Death)
            SaveableDemoSection(
                title = "2. 'rememberSaveable' Input (Persistent)",
                description = "Type text here and rotate the device. The input text stays perfectly preserved because it's bundled into the Android Bundle architecture."
            ) {
                var persistentInput by rememberSaveable { mutableStateOf("") }

                TextField(
                    value = persistentInput,
                    onValueChange = {
                        persistentInput = it
                    },
                    placeholder = {
                        Text("Saved automatically!")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primaryContainer.copy(
                            alpha = 0.3f
                        )
                    )
                )
            }
        }
    }
}

@Composable
fun SaveableDemoSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(
            modifier = Modifier.height(12.dp)
        )
        content()
    }
}