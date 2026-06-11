package com.anshul1507.composesamplefirst.examples.ui.general

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThemeModeScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    val isSystemDark = isSystemInDarkTheme()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Theme & Dark Mode"
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
            // 1. Observing the Active Dark Mode State Token
            ThemeDisplayCard(
                title = "1. Configuration Inspector"
            ) {
                Text(
                    text = "Is system Dark Mode currently enabled?",
                    style = MaterialTheme.typography.bodyLarge
                )
                Text(
                    if (isSystemDark) "YES (Dark Scheme Active)" else "NO (Light Scheme Active)",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = if (isSystemDark) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.primary
                )
            }

            // 2. Accessing Semantic Colors instead of hardcoding hex codes
            ThemeDisplayCard(
                title = "2. Color tokens over hardcoded hex"
            ) {
                Text(
                    text = "In production, never use hardcoded values like Color(0xFFFFFFFF). Use semantic Material tokens directly",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = Modifier.padding(12.dp)
                    ) {
                        Text(
                            text = "Card Container color: surfaceVariant",
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = "Card Text color: onSurfaceVariant",
                            style = MaterialTheme.typography.bodySmall
                        )
                    }
                }
            }

            // 3. Injecting an isolated sub-theme locally (Forced overrides)
            ThemeDisplayCard(
                title = "3. Isolated Theme Overrides"
            ) {
                Text(
                    text = "Need a specific section (like a checkout panel or video preview) to always stay dark, regardless of system state?",
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(12.dp))

                MaterialTheme(
                    colorScheme = darkColorScheme() //Forcing a localized Dark Mode subset
                ) {
                    Surface(
                        color = MaterialTheme.colorScheme.surfaceContainerHighest,
                        shape = MaterialTheme.shapes.medium,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(
                                text = "Forced Dark Area",
                                style = MaterialTheme.typography.titleSmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Text(
                                text = "Even if your phone is in light mode, this box adapts strictly to dark specifications.",
                                style = MaterialTheme.typography.bodyMedium,
                                color = MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ThemeDisplayCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold
        )
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 8.dp),
            color = MaterialTheme.colorScheme.outlineVariant
        )
        content()
    }
}