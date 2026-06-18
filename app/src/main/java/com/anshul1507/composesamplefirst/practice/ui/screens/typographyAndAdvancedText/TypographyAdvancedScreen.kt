package com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.LinkAnnotation
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLinkStyles
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withLink
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anshul1507.composesamplefirst.R

// Defining a Custom Production Font Family
val PoppinsFontFamily = FontFamily(
    Font(resId = R.font.poppins_regular, weight = FontWeight.Normal),
    Font(resId = R.font.poppins_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(resId = R.font.poppins_medium, weight = FontWeight.Medium),
    Font(resId = R.font.poppins_bold, weight = FontWeight.Bold)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TypographyAdvancedScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var consoleMessage by remember { mutableStateOf("Click a local text link action to run code...") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Advanced Typography") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate back to Dashboard"
                        )
                    }
                }
            )
        }, modifier = modifier
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()), // Enables vertical scrolling if items overflow
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            // 1. Applying Global Custom Font Family & Styles
            TextSection(
                title = "1. Global Font Styling",
                description = "Overriding default system typography to inject a fully custom branded font family."
            ) {
                Text(
                    text = "This paragraph renders using the Poppins custom typography sheet configuration at regular weight.",
                    fontFamily = PoppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )
            }

            // 2. Mixing Weights & Colors inline using AnnotatedString (The Spannable Requirement)
            TextSection(
                title = "2. Inline Mixed Weights & Colors (AnnotatedString)",
                description = "Combining bold keywords, customized styling markers, and inline colors within a single string composable."
            ) {
                val inlineMixedText = buildAnnotatedString {
                    append("To develop core ")

                    //Push a custom styling span into the stack
                    withStyle(
                        style = SpanStyle(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        append("Android Architecture")
                    }

                    append(", engineers must bridge the gap between basic framework code syntax and real-world ")

                    withStyle(
                        style = SpanStyle(
                            fontStyle = FontStyle.Italic,
                            color = MaterialTheme.colorScheme.tertiary
                        )
                    ) {
                        append("production performance engineering")
                    }
                }

                Text(
                    text = inlineMixedText,
                    fontFamily = PoppinsFontFamily,
                    fontSize = 15.sp,
                    lineHeight = 22.sp
                )
            }

            // 3: Inline Hyperlinks & Custom Local Callbacks
            TextSection(
                title = "3. Inline Clickable Elements",
                description = "Mixing production-grade web hyperlinks and specialized internal lambdas inside a single paragraph."
            ) {
                val linkedParaText = buildAnnotatedString {
                    append("Explore the core open-source repository directly on ")

                    // Adding an external web hyperlink
                    withLink(
                        link = LinkAnnotation.Url(
                            url = "https://github.com/Anshul1507/JetpackComposeFoundations",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.primary,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            )
                        )
                    ) {
                        append("Github")
                    }

                    append(". Alternatively, you can click here to execute an ")

                    // Adding an internal custom callback to trigger in-app behavior
                    withLink(
                        link = LinkAnnotation.Clickable(
                            tag = "CUSTOM_CONSOLE_ACTION",
                            styles = TextLinkStyles(
                                style = SpanStyle(
                                    color = MaterialTheme.colorScheme.tertiary,
                                    fontWeight = FontWeight.Bold,
                                    textDecoration = TextDecoration.Underline
                                )
                            ),
                            linkInteractionListener = {
                                consoleMessage = "Local inline link captured! Running business logic..."
                            }
                        )
                    ) {
                        append("internal app script")
                    }

                    append(".")
                }

                Text(
                    text = linkedParaText,
                    fontSize = 16.sp,
                    lineHeight = 24.sp
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                Card(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = consoleMessage,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            }

            // 4: Text Selection Controls
            TextSection(
                title = "4. Copy/Paste Support (SelectionContainer)",
                description = "By default, Text is not copyable. Wrap your elements inside a SelectionContainer to enable long-press copy hooks."
            ) {
                SelectionContainer {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                       Text(
                           text = "Long press on this text string to bring up the Android system copy-paste handle overlay layout.",
                           fontSize = 15.sp,
                           lineHeight = 22.sp
                       )
                        Text(
                            text = "Transactional Ref ID: #TRX-0000-COMPOSE",
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.outline
                        )
                    }
                }
            }
        }
    }
}

/**
 * A reusable component to organize our feature screen.
 */
@Composable
fun TextSection(
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
            fontWeight = FontWeight.Bold,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        HorizontalDivider(
            modifier = Modifier.padding(12.dp)
        )
        content()
    }
}