
package com.example.politipal.ui

import android.util.Log
import android.view.WindowInsets
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.StarBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Shapes
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.data.Email
import com.example.politipal.data.Rep
import com.example.politipal.ui.components.RepListItem
import com.example.politipal.ui.components.ReplyEmailListItem
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.TextField
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
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
    val emailLazyListState = rememberLazyListState()
    LaunchedEffect(key1 = contentType) {
        if (contentType == PolitipalContentType.SINGLE_PANE && !homeUIState.isDetailOnlyOpen) {
            closeDetailScreen()
        }
    }

        LazyColumn (
            modifier = modifier
                .fillMaxWidth(),

        ) {
            stickyHeader {
                Surface(
                    Modifier
                        .fillParentMaxWidth()
                        .padding(top = 10.dp, bottom = 10.dp)
                    ){


                        RepSearchBar(modifier = modifier)


                }}
            val reps = homeUIState.reps
            items(items = reps, key = { it.id }) { rep ->
                RepResultListView(
                    modifier = modifier,
                    reps = rep,
                    //email = homeUIState.emails,
                    //openedEmail = replyHomeUIState.openedEmail,
                    //selectedEmailIds = replyHomeUIState.selectedEmails,
                    toggleRepSelection = toggleSelectedRep,
                    //emailLazyListState = emailLazyListState,
                    //navigateToDetail = navigateToDetail
                )
            }
        }
    }



@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun RepSearchBar(
    modifier: Modifier

){
    // Variables for component
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf(false) }
    var boxVisible by remember {
        mutableStateOf(false)
    }


    // Inner Component layout for just search bar

        Box(modifier = modifier
            .fillMaxWidth()
            .background(Color(0xFFF9F1F8))
            .padding(bottom = 5.dp, top = 40.dp, start = 10.dp, end = 10.dp)
            ) {
                Column {
                    Row (modifier.padding(bottom = 10.dp)){

                        // Helpful Tutorial: https://www.composables.com/material3/dockedsearchbar/video
                        // Wanted to make it look nice and have a fade when scrolling up, for later

                        DockedSearchBar(
                            modifier = Modifier
                                .wrapContentWidth()
                                .width(330.dp),
                            query = text,
                            onQueryChange = { text = it }, // refers to actual text in search bar
                            onSearch = { active = false },
                            active = false,
                            onActiveChange = { active = it },
                            placeholder = { Text(text = "Search Representatives") },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Search,
                                    contentDescription = "Search Icon"
                                )
                            },
                            trailingIcon = {
                                if (active) {
                                    Icon(
                                        modifier = Modifier.clickable {
                                            if (text.isNotEmpty()) {
                                                text = ""
                                            } else {
                                                active = false
                                            }
                                        },
                                        imageVector = Icons.Default.Close,
                                        contentDescription = "Close Icon"
                                    )
                                }
                            }

                        ) {}
                        // Filter Button
                        FloatingActionButton(
                            onClick = { boxVisible = !boxVisible},
                            modifier = Modifier
                                .padding(start = 5.dp),
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = "Favorite",
                                tint = MaterialTheme.colorScheme.outline
                            )
                        }
                    }
                        AnimatedVisibility(visible = boxVisible) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(Color(0xFFF0E5F4), RoundedCornerShape(15.dp)) // Box styling

                            ) {
                                Box(modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(all = 10.dp)){
                                    Column {
                                        Text(
                                            text = "Demograhics",
                                            fontSize = 16.sp, // Set font size
                                            fontWeight = FontWeight.Bold, // Set font weight for header
                                            modifier = Modifier.padding(5.dp) // Optionally, add padding around the text
                                        )
                                        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                                        FlowRow (modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Start){

                                            RepFilterChip(modifier = modifier, label = "Female")
                                            RepFilterChip(modifier = modifier, label = "Male")
                                            RepFilterChip(modifier = modifier, label = "Democrat")
                                            RepFilterChip(modifier = modifier, label = "Republican")
                                            RepFilterChip(modifier = modifier, label = "Senate")
                                            RepFilterChip(modifier = modifier, label = "House")
                                        }

                                        Text(
                                            text = "Regions",
                                            fontSize = 16.sp, // Set font size
                                            fontWeight = FontWeight.Bold, // Set font weight for header
                                            modifier = Modifier.padding(5.dp) // Optionally, add padding around the text
                                        )
                                        HorizontalDivider(thickness = 1.dp, color = Color.Gray)
                                        Row {
                                            RepFilterChip(modifier = modifier, label = "Long Beach")
                                            RepFilterChip(modifier = modifier, label = "Los Angeles")
                                    }
                                }
                            }
                    }
                    }
                }

        




    }}


@Composable
fun RepResultListView(
    modifier: Modifier,
    reps: Rep,
    toggleRepSelection: (String) -> Unit,
    ){
        ElevatedCard(
            onClick = { /* Do something */ },
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
            modifier = Modifier
                .padding(horizontal = 30.dp, vertical = 4.dp)
                .height(150.dp),

            ){
            Column(
                modifier = Modifier
                    .fillMaxWidth()

                    .padding(20.dp),
            ) {

                Text(text = reps.name)
            }
        }
        Spacer(Modifier.height(20.dp))

    }

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StateDropdownMenu(){
    val options = listOf("Cupcake", "Donut", "Eclair", "Froyo", "Gingerbread")
    var expanded by remember { mutableStateOf(false) }
    var text by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = it },
    ) {
        TextField(
            // The `menuAnchor` modifier must be passed to the text field to handle
            // expanding/collapsing the menu on click. A read-only text field has
            // the anchor type `PrimaryNotEditable`.
            modifier = Modifier.menuAnchor(),
            value = text,
            onValueChange = {},
            readOnly = true,
            singleLine = true,
            label = { Text("Label") },
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    text = { Text(option, style = MaterialTheme.typography.bodyLarge) },
                    onClick = {
                        text = option
                        expanded = false
                    },
                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                )
            }
        }
    }
}

@Composable

fun RepFilterChip(
    modifier: Modifier,
    label: String,
){
    var selected by remember { mutableStateOf(false) }
    FilterChip(
        modifier = Modifier.padding( end = 5.dp),
        onClick = { selected = !selected },
        label = {
            Text(label)
        },
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Done icon",
                    modifier = Modifier.size(FilterChipDefaults.IconSize)
                )
            }
        } else {
            null
        }


    )
}