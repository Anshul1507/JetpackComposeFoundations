package com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdvancedTextScreen(
    onBack: () -> Unit, modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Text Samples") }, navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back to Dashboard"
                        )
                    }
                }, colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.onSecondaryContainer
                )
            )
        }, modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState()), // Enables vertical scrolling if items overflow
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // Example 1: Standard Design Token Theme
            TextSection(
                title = "1. Global Typography Tokens"
            ) {
                Text(
                    text = "This text uses the headlineMedium token.",
                    style = MaterialTheme.typography.headlineMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "This text uses the bodyMedium token for generic paragraphs.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }

            // Example 2: Handling Text Truncation & Overflow Conditions
            TextSection(
                title = "2. Overflow Control (Ellipsize)"
            ) {
                Text(
                    text = "This is a very long string that would normally wrap down into multiple rows on smaller mobile screens, but we have explicitly clamped it down to a single layout line limit using text overflow parameters.",
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            // Example 3: Complex Multi-Style Strings (Annotated Strings)
            TextSection(
                title = "3. Multiple Inline Styles (Annotated String)"
            ) {
                val mixedStyleString = buildAnnotatedString {
                    append("You can format strings inline. Make things ")

                    //Push a temporary bold style structure
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color.Red)) {
                        append("BOLD AND RED")
                    }

                    append(", switch instantly over to ")

                    //Push a temporary italicized style structure
                    withStyle(style = SpanStyle(fontStyle = FontStyle.Italic, fontSize = 20.sp)) {
                        append("italicized large font")
                    }

                    append(", or fallback cleanly to base text defaults.")
                }

                Text(
                    text = mixedStyleString,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

/**
 * A reusable component to organize our feature screen.
 */
@Composable
fun TextSection(
    title: String, modifier: Modifier = Modifier, content: @Composable () -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary
        )
        HorizontalDivider(modifier = Modifier.padding(vertical = 8.dp))
        content()
    }
}