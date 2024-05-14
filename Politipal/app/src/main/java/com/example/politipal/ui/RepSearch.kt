
package com.example.politipal.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.data.Rep
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.politipal.ui.theme.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun RepSearch(
    contentType: PolitipalContentType,
    homeUIState: homeUIState,
    navigationType: PolitipalNavigationType,
    displayFeatures: List<DisplayFeature>,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,     // Function to nav to detail page
    toggleSelectedRep: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val favoriteReps = remember { mutableStateOf(setOf<String>()) }
    LaunchedEffect(key1 = contentType) {
        if (contentType == PolitipalContentType.SINGLE_PANE && !homeUIState.isDetailOnlyOpen) {
            closeDetailScreen()
        }
    }

    Scaffold(
        modifier = Modifier.semantics {
            var testTagsAsResourceId = true
        },
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        contentColor = MaterialTheme.colorScheme.secondaryContainer,
    ) { padding ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(padding)
        )
        {
            val repId = "senator_james_charles"  // Example representative ID
            val isFavorite = favoriteReps.value.contains(repId)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(8),
            ) {
                Column(
                    modifier = modifier
                        .padding(10.dp)
                )  {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .padding(vertical = 15.dp),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                text = "Senator James Charles",
                                style = MaterialTheme.typography.titleLarge,
                                color = MaterialTheme.colorScheme.scrim
                            )

                        }
                        //NEED TO ADD FAVORITES BUTTON FUNCTIONALITY
                        IconButton(
                            onClick = {
                                favoriteReps.value = if (isFavorite) {
                                    favoriteReps.value - repId
                                } else {
                                    favoriteReps.value + repId
                                }
                            },
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = Icons.Default.StarBorder,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) Color.Yellow else MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                    // New Row added here
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Add your components here
                        Text(
                            text = "M, 1995, R, California",
                            style = MaterialTheme.typography.bodyMedium,
                        )

                    }

                    // New Row added here
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Add your components here
                        Text(
                            text = "6005 Obispo Ave, Long Beach, CA 90805",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )

                    }
                    // New Row added here
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Add your components here
                        Text(
                            text = "Website: https://jamescharles.senate.gov",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                            color = Color.Blue
                        )

                    }
                    Divider(modifier = Modifier.padding(vertical = 20.dp))

                    // New Row added here
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Add your components here
                        Text(
                            text = "Ballotpedia ID: James Charles",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )

                    }

                    // New Row added here
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "OpenSecrets ID: N00007833",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )

                    }

                    // New Row added here
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        // Add your components here
                        Text(
                            text = "Facebook: SenJamesCharles",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Twitter: SenJamesCharles",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )

                    }

                    // New Row added here
                    /*Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 25.dp)
                            .padding(horizontal = 75.dp)
                    ) {
                        ExtendedFloatingActionButton(
                            text = { Text("Contact Form") },
                            onClick = { /* TODO: Add your click handling logic here */ },
                            icon = { Icon(Icons.Default.People, contentDescription = "Left Arrow") }
                        )
                    }*/
                }
            }
        }
    }
}