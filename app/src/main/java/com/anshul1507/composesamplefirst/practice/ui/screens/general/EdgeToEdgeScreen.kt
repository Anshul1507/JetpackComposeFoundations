package com.anshul1507.composesamplefirst.practice.ui.screens.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.windowInsetsTopHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EdgeToEdgeScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    // We are not using Scaffold Here to get the actual root background
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        // 1. Safe Top Status Bar Container
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsTopHeight(WindowInsets.statusBars)
                .background(MaterialTheme.colorScheme.primary)
        )

        // Custom Title Header Area
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.primary)
                .padding(horizontal = 4.dp, vertical = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
            Text(
                text = "Edge-to-Edge Rendering",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }

        // 2. Scrollable Core Body Content
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            InsetDemoCard(
                title = "1. Why Edge-to-Edge matter?"
            ) {
                Text("Look at the top of your screen! The blue header box wraps perfectly up and behind the system time, Wi-Fi icons, and battery meter. In traditional apps, this was locked behind a solid black box.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            InsetDemoCard(
                title = "2. WindowInsets.safeContent"
            ) {
                Text(
                    text = "By referencing the WindowInsets APIs, you can programmatically extract structural padding information about physical device obstructions. This is vital for adapting elegantly to foldable phones, chin bezels, and dynamic island cutouts.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            InsetDemoCard(
                title = "3. Keyboard Inset Integration"
            ) {
                Text(
                    text = "WindowInsets can also track the software IME keyboard! You can apply Modifier.windowInsetsPadding(WindowInsets.ime) to a chat entry box to force it to slide up smoothly right on top of the keyboard when a user taps an input field.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }

        // 3. Safe Bottom  Navigation Bar
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .windowInsetsPadding(WindowInsets.navigationBars)
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Fixed Sticky Button")
            }
        }
    }
}

@Composable
fun InsetDemoCard(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f)
        )
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Spacer(
                modifier = Modifier.height(8.dp)
            )
            content()
        }
    }
}