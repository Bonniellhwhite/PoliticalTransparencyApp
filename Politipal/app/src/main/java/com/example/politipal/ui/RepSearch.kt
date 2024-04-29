
package com.example.politipal.ui

import android.view.WindowInsets
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DockedSearchBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.Modifier
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
                .fillMaxWidth()
                .padding(top = 10.dp),

        ) {
            stickyHeader {

                Surface(Modifier.fillParentMaxWidth().padding(bottom = 20.dp)
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepSearchBar(
    modifier: Modifier
){
    // Variables for component
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf((false)) }

    // Inner Component layout for just search bar
    Box(modifier = modifier
        .fillMaxSize()
        .background(Color(0xFFF9F1F9))
        .padding(bottom = 5.dp, top= 50.dp, start = 10.dp, end = 10.dp)
        ) {

        // Helpful Tutorial: https://www.composables.com/material3/dockedsearchbar/video
        // Wanted to make it look nice and have a fade when scrolling up, for later
        DockedSearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = text,
            onQueryChange = {text = it }, // refers to actual text in search bar
            onSearch = {active = false },
            active = active,
            onActiveChange = { active = it },
            placeholder = {Text(text = "Search Representatives")},
            leadingIcon = { Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")},
            trailingIcon = {
                if(active){
                    Icon(
                        modifier = Modifier.clickable{
                            if(text.isNotEmpty()){
                                text = ""
                            }else{
                                active = false
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close Icon")
                }
            }

        ) {
            // Idk if i want stuff here we will see
        }
    }
}

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