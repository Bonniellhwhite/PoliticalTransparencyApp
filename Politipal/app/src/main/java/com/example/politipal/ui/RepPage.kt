
package com.example.politipal.ui

import android.app.ActivityManager.AppTask
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.R
import com.example.politipal.data.Rep
import com.example.politipal.ui.theme.AppTheme
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepPage(
    contentType: PolitipalContentType,
    homeUIState: homeUIState,
    navigationType: PolitipalNavigationType,
    displayFeatures: List<DisplayFeature>,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    rep: Rep

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
                                text = "${rep.type} ${rep.firstName} ${rep.surname}",
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
                            text = "${rep.gender} ${rep.birthday} ${rep.party} ${rep.state}",
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
                            text = "${rep.address}",
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
                            text = "Website: ${rep.url}",
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
                            text = "Ballotpedia ID: ${rep.ballotpediaID}",
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
                            text = "OpenSecrets ID: ${rep.opensecretsID}",
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
                            text = "Facebook: ${rep.facebook}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )

                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Twitter: ${rep.twitter}",
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