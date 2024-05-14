
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.People
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
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
    modifier: Modifier = Modifier,
    rep: Rep

) {
    val viewModel = HomeViewModel()
    LaunchedEffect(key1 = contentType) {
        if (contentType == PolitipalContentType.SINGLE_PANE && !homeUIState.isDetailOnlyOpen) {
            //closeDetailScreen()
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        // Crystal TODO?

        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(all = 50.dp)
                .background(MaterialTheme.colorScheme.secondaryContainer)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .weight(1f)
                        .padding(vertical = 15.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "${rep.type} ${rep.firstName} ${rep.surname}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.scrim
                    )

                }
                //NEED TO ADD FAVORITES BUTTON FUNCTIONALITY
                IconButton(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceContainerHigh)
                ) {
                    Icon(
                        imageVector = Icons.Default.StarBorder,
                        contentDescription = "Favorite",
                        tint = MaterialTheme.colorScheme.outline
                    )
                }
            }
            // New Row added here
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 25.dp)
                    .padding(20.dp)
            ) {
                // Add your components here
                Text(
                    text = "${rep.gender} ${rep.birthday} ${rep.party} ${rep.state}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                )

            }

            // New Row added here
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 65.dp)
                    .padding(20.dp)
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
                    .padding(vertical = 85.dp)
                    .padding(20.dp)
            ) {
                // Add your components here
                Text(
                    text = "${rep.url}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                    color = Color.Blue
                )

            }

            // New Row added here
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 105.dp)
                    .padding(20.dp)
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
                    .padding(vertical = 125.dp)
                    .padding(20.dp)
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
                    .padding(vertical = 145.dp)
                    .padding(20.dp)
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
                    .padding(vertical = 165.dp)
                    .padding(20.dp)
            ) {
                // Add your components here
                Text(
                    text = "Twitter: ${rep.twitter}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                )

            }

            // New Row added here
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 250.dp)
                    .padding(horizontal = 75.dp)
            ) {
                ExtendedFloatingActionButton(
                    text = { Text("Contact Form") },
                    onClick = { /* TODO: Add your click handling logic here */ },
                    icon = { Icon(Icons.Default.People, contentDescription = "Left Arrow") }
                )
            }

        }
    }
}