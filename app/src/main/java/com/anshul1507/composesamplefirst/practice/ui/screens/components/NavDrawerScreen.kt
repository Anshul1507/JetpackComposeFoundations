package com.anshul1507.composesamplefirst.practice.ui.screens.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.DismissibleNavigationDrawer
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import org.w3c.dom.Text

enum class DrawerVariant {
    MODAL,
    DISMISSABLE
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawerScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var currentVariant by remember { mutableStateOf(DrawerVariant.MODAL) }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val coroutineScope = rememberCoroutineScope()

    val drawerContent = @Composable {
        ModalDrawerSheet {
            Spacer(
                modifier = Modifier.height(16.dp)
            )
            Text(
                text = "Workspace Options",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp)
            )
            HorizontalDivider()
            NavigationDrawerItem(
                label = {
                    Text(
                        text = "Toggle to Dismissable Variant"
                    )
                },
                selected = currentVariant == DrawerVariant.DISMISSABLE,
                onClick = {
                    currentVariant = DrawerVariant.DISMISSABLE
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
            NavigationDrawerItem(
                label = {
                    Text(
                        text = "Toggle to Modal Variant"
                    )
                },
                selected = currentVariant == DrawerVariant.MODAL,
                onClick = {
                    currentVariant = DrawerVariant.MODAL
                    coroutineScope.launch {
                        drawerState.close()
                    }
                },
                modifier = Modifier.padding(16.dp)
            )
        }
    }

    // Based on selected variant state, wrap the scaffold with the corresponding Material layout block
    when (currentVariant) {
        DrawerVariant.MODAL -> {
            ModalNavigationDrawer(
                drawerState = drawerState,
                drawerContent = drawerContent
            ) {
                MainScreenContent(
                    drawerState = drawerState,
                    variantName = "Modal Navigation Drawer",
                    onBack = onBack
                )
            }
        }

        DrawerVariant.DISMISSABLE -> {
            DismissibleNavigationDrawer(
                drawerState = drawerState,
                drawerContent = drawerContent
            ) {
                MainScreenContent(
                    drawerState = drawerState,
                    variantName = "Dismissable Navigation Drawer",
                    onBack = onBack
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    drawerState: DrawerState,
    variantName: String,
    onBack: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Navigation Drawers"
                    )
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    IconButton(
                        onClick = {
                            coroutineScope.launch {
                                if (drawerState.isClosed) {
                                    drawerState.open()
                                } else {
                                    drawerState.close()
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Menu Toggle"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = variantName,
                    style = MaterialTheme.typography.titleLarge
                )
                Spacer(
                    modifier = Modifier.height(8.dp)
                )
                Button(
                    onClick = {
                        coroutineScope.launch {
                            drawerState.open()
                        }
                    }
                ) {
                    Text(
                        text = "Swipe or Tap to open Drawer"
                    )
                }
            }
        }
    }
}