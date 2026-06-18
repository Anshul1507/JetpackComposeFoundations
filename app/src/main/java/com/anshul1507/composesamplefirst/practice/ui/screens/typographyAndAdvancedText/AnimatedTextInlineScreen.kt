package com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.InlineTextContent
import androidx.compose.foundation.text.appendInlineContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.Placeholder
import androidx.compose.ui.text.PlaceholderVerticalAlign
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimatedTextInlineScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    //State to derive the dynamic swapping and text transitions
    var isSecureState by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Inline Text Animations"
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
            // Trigger Toggle Button
            Button(
                onClick = {
                    isSecureState = !isSecureState
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    if (isSecureState) "Switch to Unsecured Mode" else "Switch to Secured Mode"
                )
            }

            // 1. Assign a unique String ID token to your placeholder slot location
            val inlineContentId = "DYNAMIC_STATUS_WIDGET"

            val annotatedParagraph = buildAnnotatedString {
                append("Your current connection profile status is currently evaluated as ")

                //2. Drop the placeholder tag token exactly where the text should part ways
                appendInlineContent(id = inlineContentId, alternateText = "[Status indicator]")

                append(". Please review your active network credentials and architecture configurations.")
            }

            // 3. Define the dimensions and alignment rules for the injected space
            val inlineContentMap = mapOf(
                inlineContentId to InlineTextContent(
                    placeholder = Placeholder(
                        width = 110.sp, // Allocate enough physical space horizontally for the animation frame
                        height = 22.sp,
                        placeholderVerticalAlign = PlaceholderVerticalAlign.Center
                    )
                ) {
                    // 4. Implement AnimatedContent inside the slot window boundary
                    AnimatedContent(
                        targetState = isSecureState,
                        transitionSpec = {
                            (slideInVertically { it } + fadeIn()) togetherWith
                                    (slideOutVertically { -it } + fadeOut())
                        },
                        label = "InlineWidgetMorph"
                    ) { targetSecure ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = if (targetSecure) Icons.Default.Lock else Icons.Default.LockOpen,
                                contentDescription = null,
                                tint = if (targetSecure) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = if (targetSecure) "SECURED" else "UNSECURED",
                                style = MaterialTheme.typography.bodyMedium,
                                fontWeight = FontWeight.Bold,
                                color = if (targetSecure) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.error
                            )
                        }
                    }
                }
            )

            // Render the complex element passing both the string block and your custom inline map scheme
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f))
            ) {
                Text(
                    text = annotatedParagraph,
                    inlineContent = inlineContentMap,
                    fontSize = 16.sp,
                    lineHeight = 26.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}