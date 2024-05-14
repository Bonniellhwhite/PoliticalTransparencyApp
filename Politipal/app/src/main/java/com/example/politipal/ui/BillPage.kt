
package com.example.politipal.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Check
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
import com.example.politipal.ui.components.ReplyProfileImage
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType
import com.example.politipal.data.Bill


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillPage(
    contentType: PolitipalContentType,
    homeUIState: homeUIState,
    navigationType: PolitipalNavigationType,
    displayFeatures: List<DisplayFeature>,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    modifier: Modifier = Modifier,
    bill: Bill,
    isOpened: Boolean = false,
    isSelected: Boolean = false
) {
    val favoriteBills= remember { mutableStateOf(setOf<String>()) }
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
            val billId = "s_36"  // Example representative ID
            val isFavorite = favoriteBills.value.contains(billId)
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp),
                shape = RoundedCornerShape(8),
            ) {
                Column(
                    modifier = modifier
                        .padding(10.dp)
                ) {
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
                                text = "${bill.type} ${bill.number}",
                                style = MaterialTheme.typography.displaySmall,
                                color = MaterialTheme.colorScheme.scrim
                            )
                        }
                        IconButton(
                            onClick = {
                                favoriteBills.value = if (isFavorite) {
                                    favoriteBills.value - billId
                                } else {
                                    favoriteBills.value + billId
                                } },
                            modifier = Modifier
                        ) {
                            Icon(
                                imageVector = Icons.Default.StarBorder,
                                contentDescription = "Favorite",
                                tint = if (isFavorite) Color.Yellow else MaterialTheme.colorScheme.outline
                            )
                        }
                    }

                    Text(
                        modifier = Modifier,
                        text = "Bill Title:",
                        style = MaterialTheme.typography.titleLarge,
                    )
                    Divider(modifier = Modifier.padding(vertical = 10.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .weight(1f)
                                .height(100.dp)  // Adjust height to fit approximately 5 lines
                        ) {
                            LazyColumn(
                                modifier = Modifier.fillMaxHeight(),
                                state = rememberLazyListState(),
                                content = {
                                    item {
                                        Text(
                                            text = "${bill.title}",
                                            style = MaterialTheme.typography.bodyLarge,
                                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                                        )
                                    }
                                }
                            )
                        }
                    }
                    Divider(modifier = Modifier.padding(vertical = 20.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Chamber of Origin: ${bill.originChamber}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Last Updated: ${bill.updateDate}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(
                            text = "Official link: ${bill.url}",
                            style = MaterialTheme.typography.bodyMedium,
                            modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                        )
                    }
                }
            }
        }
    }
}
