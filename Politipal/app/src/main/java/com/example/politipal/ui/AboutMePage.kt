
package com.example.politipal.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.R
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.testTagsAsResourceId
import androidx.compose.material.icons.filled.Archive
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Folder
import androidx.compose.material.icons.filled.StarBorder
import com.example.politipal.data.ProfilePopularList
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType


const val username = "Alexa"
const val my_description = "A college student interested in politics - in search of political transparency."
val profilePopularList = listOf(
    ProfilePopularList("H.R. 0345", "Denouncing the Biden administration's immigration policies", "25", "Kotlin"),
    ProfilePopularList("Representative Brown", "Party: Nonpartisan", "9", "Kotlin"),
    ProfilePopularList("S. 36", "A concurrent resolution authorizing the use of Emancipation Hall in the Capitol Visitor Center for an event to celebrate the birthday of King Kamehameha I.", "45", "Kotlin")
)

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AboutMePage(
    contentType: PolitipalContentType,
    homeUIState: homeUIState,
    navigationType: PolitipalNavigationType,
    displayFeatures: List<DisplayFeature>,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = contentType) {
        if (contentType == PolitipalContentType.SINGLE_PANE && !homeUIState.isDetailOnlyOpen) {
            closeDetailScreen()
        }
    }
            Scaffold(
                modifier = Modifier.semantics {
                    testTagsAsResourceId = true
                },
                containerColor = MaterialTheme.colorScheme.secondaryContainer,
                contentColor = MaterialTheme.colorScheme.secondaryContainer,
            ) { padding ->
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .padding(padding)
                ) {
                    TopProfileLayout()
                    MainProfileContent()
                }
            }
        }

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun TopProfileLayout() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Row(
                modifier = Modifier.padding(vertical = 5.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .weight(1f)
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )

                    Text(
                        text = username,
                        style = MaterialTheme.typography.displaySmall
                    )
                }
            }

            Text(
                modifier = Modifier.padding(vertical = 5.dp),
                text = my_description,
                style = MaterialTheme.typography.titleLarge,
            )
        }
    }
}

@Composable
fun ImageTextContent(
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Spacer(modifier = Modifier.width(5.dp))
        text()
        Spacer(modifier = Modifier.width(10.dp))
    }
}

@Composable
fun MainProfileContent() {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(8),
    ) {
        Column(modifier = Modifier.padding(5.dp)) {
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Recently Favorited",
                style = MaterialTheme.typography.titleLarge,
            )
            PopularContentList()

            Divider(modifier = Modifier.padding(vertical = 20.dp))

            GitContentItem(
                modifier = Modifier.padding(vertical = 2.dp),
                icon = {
                    Icon(
                        Icons.Default.StarBorder,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(6.dp)
                    )
                },
                text = {
                    Text(
                        text = "Favorites",
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                endItem = {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "60"
                    )
                }
            )
            GitContentItem(
                modifier = Modifier.padding(vertical = 2.dp),
                icon = {
                    Icon(
                        Icons.Default.Archive,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(6.dp)
                    )
                },
                text = {
                    Text(
                        text = "Comments",
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                endItem = {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "85"
                    )
                }
            )
            GitContentItem(
                modifier = Modifier.padding(vertical = 2.dp),
                icon = {
                    Icon(
                        Icons.Default.Folder,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(6.dp)
                    )
                },
                text = {
                    Text(
                        text = "Contributions",
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                endItem = {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "19"
                    )
                }
            )
            GitContentItem(
                modifier = Modifier.padding(vertical = 2.dp),
                icon = {
                    Icon(
                        Icons.Default.Favorite,
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .padding(6.dp)
                    )
                },
                text = {
                    Text(
                        text = "Reactions",
                        style = MaterialTheme.typography.labelLarge,
                    )
                },
                endItem = {
                    Text(
                        modifier = Modifier.padding(5.dp),
                        text = "1239"
                    )
                }
            )
        }
    }
}

@Composable
fun PopularContentList() {
    LazyRow {
        items(
            items = profilePopularList,
            itemContent = {
                Surface(
                    modifier = Modifier
                        .width(250.dp)
                        .padding(6.dp),
                    shape = RoundedCornerShape(8),
                    border = BorderStroke(0.1.dp, MaterialTheme.colorScheme.onBackground)
                ) {
                    Column(modifier = Modifier.padding(5.dp)) {
                        Row(
                            modifier = Modifier.padding(vertical = 5.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(5.dp))
                            Text(
                                text = it.name,
                                style = MaterialTheme.typography.titleLarge,
                            )
                        }

                        Text(
                            modifier = Modifier.padding(vertical = 5.dp),
                            text = it.description,
                            style = MaterialTheme.typography.bodyMedium, maxLines = 2,
                        )

                        Row(
                            modifier = Modifier.padding(vertical = 7.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                    }
                }
            }
        )
    }
}

@Composable
fun GitContentItem(
    modifier: Modifier = Modifier,
    icon: @Composable () -> Unit,
    text: @Composable () -> Unit,
    endItem: @Composable () -> Unit,
) {
    Row(
        modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon()
        Column(
            modifier = Modifier
                .padding(horizontal = 5.dp)
                .weight(1f)
        ) {
            text()
        }
        endItem()
    }
}
