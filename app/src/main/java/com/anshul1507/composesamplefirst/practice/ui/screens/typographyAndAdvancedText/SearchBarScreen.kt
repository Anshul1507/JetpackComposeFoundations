package com.anshul1507.composesamplefirst.practice.ui.screens.typographyAndAdvancedText

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    var searchQuery by remember { mutableStateOf("") }
    var isSearchActive by remember { mutableStateOf(false) }

    // Mock data to represent indexed search entities
    val frameworkComponents = remember {
        listOf(
            "Box Layout", "Row Component", "Column Stack", "LazyColumn List",
            "LazyRow List", "Modifier Chains", "State Hoisting", "LaunchedEffect",
            "RememberCoroutineScope", "OutlinedTextField", "ModalNavigationDrawer"
        )
    }

    //Real time query matching execution
    val filteredComponents = remember(searchQuery) {
        frameworkComponents.filter {
            it.contains(searchQuery, ignoreCase = true)
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Search Components"
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
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(28.dp)
        ) {

            //1. Material 3 SearchBar Component Implementation
            SearchBar(
                query = searchQuery,
                onQueryChange = {
                    searchQuery = it
                },
                onSearch = {
                    // Runs when the user presses the keyboard search action
                    isSearchActive = false
                },
                active = isSearchActive,
                onActiveChange = {
                    isSearchActive = it
                },
                placeholder = {
                    Text("Search architecture kit...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )
                },
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(
                            onClick = {
                                searchQuery = ""
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear icon"
                            )
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                // 2. Expandable Query Overlay panel content
                LazyColumn(
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredComponents) { component ->
                        Text(
                            text = component,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    searchQuery = component
                                    isSearchActive = false
                                }
                                .padding(vertical = 4.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }

                    if (filteredComponents.isEmpty()) {
                        item {
                            Text(
                                text = "No matching core layouts found.",
                                color = MaterialTheme.colorScheme.outline,
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
            }

            // Static Main View layer visible when the search bar drop down is collapsed
            if (!isSearchActive) {
                Text(
                    text = "All Available Catalog Items",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        top = 8.dp
                    )
                )

                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(frameworkComponents) {item ->
                        Card(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = item,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}