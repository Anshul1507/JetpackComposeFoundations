package com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldCustomScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var customStyledInput by remember { mutableStateOf("") }
    var numericInput by remember { mutableStateOf("") }

    //Derived state determining validation rules instantly
    val isNumberInvalid = remember(numericInput) {
        numericInput.isNotEmpty() && numericInput.length < 4
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Custom TextFields"
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
            // 1: Custom Styled OutlinedTextField
            InputSection(
                title = "1. Custom Branded OutlinedTextField",
                description = "Modifying the border colors, focused highlights, and container shapes of an input field."
            ) {
                OutlinedTextField(
                    value = customStyledInput,
                    onValueChange = {
                        customStyledInput = it
                    },
                    label = {
                        Text(
                            text = "Enter Workspace Domain"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "e.g., compose"
                        )
                    },
                    modifier = Modifier.fillMaxWidth(),
                    shape = MaterialTheme.shapes.medium, //custom container corners
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = MaterialTheme.colorScheme.primary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.outlineVariant,
                        focusedLabelColor = MaterialTheme.colorScheme.primary
                    )
                )
            }

            // 2: Numeric Keyboard & Instant Error Validation State
            InputSection(
                title = "2. Numeric Input & Real-Time Validation",
                description = "Forcing a clean numeric keypad and rendering semantic material error states instantly via state checking."
            ) {
                OutlinedTextField(
                    value = numericInput,
                    onValueChange = { newValue ->
                        //allow incoming strings only numbers
                        if (newValue.all { it.isDigit() }) {
                            numericInput = newValue
                        }
                    },
                    label = {
                        Text(
                            text = "Secure Pin Code"
                        )
                    },
                    placeholder = {
                        Text(
                            text = "Minimum 4 digits"
                        )
                    },
                    isError = isNumberInvalid,
                    supportingText = {
                        if (isNumberInvalid) {
                            Text(
                                text = "Pin must be at least 4 digits long.",
                                color = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    trailingIcon = {
                        if (isNumberInvalid) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Error Indicator",
                                tint = MaterialTheme.colorScheme.error
                            )
                        }
                    },
                    // Restricting keyboard parameters explicitly
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun InputSection(
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
