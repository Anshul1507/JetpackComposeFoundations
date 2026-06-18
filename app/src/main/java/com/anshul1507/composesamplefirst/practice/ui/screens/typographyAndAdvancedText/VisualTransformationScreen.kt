package com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VisualTransformationScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var passwordInput by remember { mutableStateOf("") }
    var isPasswordVisible by remember { mutableStateOf(false) }
    var creditCardInput by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Transformations & Masks"
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
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {
            // 1. Password Masking with Toggle Action
            TransformationSection(
                title = "1. Secure Password Entry",
                description = "Swaps between built-in PasswordVisualTransformation and VisualTransformation. None seamlessly based on state."
            ) {
                OutlinedTextField(
                    value = passwordInput,
                    onValueChange = {
                        passwordInput = it
                    },
                    label = {
                        Text(
                            text = "Account Password"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Password
                    ),
                    trailingIcon = {
                        val icon = if (isPasswordVisible) Icons.Default.VisibilityOff else Icons.Default.Visibility
                        IconButton(
                            onClick = {
                                isPasswordVisible = !isPasswordVisible
                            }
                        ) {
                            Icon(
                                imageVector = icon, contentDescription = "Toggle Visibility"
                            )
                        }
                    }
                )
            }

            // 2. Custom Credit Card Multi-Row Spacing
            TransformationSection(
                title = "2. Credit Card Formatting (Hyphen Grouping)",
                description = "Injects grouping dividers visually on screen while preserving raw numerical strings within the business logic state."
            ) {
                OutlinedTextField(
                    value = creditCardInput,
                    onValueChange = { input ->
                        // Reject non-digit changes and limit to 16 digits max
                        if (input.all { it.isDigit() } && input.length <= 16) {
                            creditCardInput = input
                        }
                    },
                    label = { Text("Card Number") },
                    placeholder = { Text("0000 0000 0000 0000") },
                    modifier = Modifier.fillMaxWidth(),
                    visualTransformation = CreditCardVisualTransformation(),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    PaddingValues(12.dp)
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text("Underlying State Value (Saved to DB):", style = MaterialTheme.typography.labelSmall)
                        Text(
                            text = creditCardInput.ifEmpty { "[Empty]" },
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun TransformationSection(
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(text = title, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
        Text(text = description, style = MaterialTheme.typography.bodySmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Spacer(modifier = Modifier.height(12.dp))
        content()
    }
}