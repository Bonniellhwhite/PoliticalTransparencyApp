
package com.example.politipal.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.data.Email
import com.example.politipal.ui.components.BillSearchBar
import com.example.politipal.ui.components.RepSearchBar
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepSearch(
    contentType: PolitipalContentType,
    homeUIState: homeUIState,
    navigationType: PolitipalNavigationType,
    displayFeatures: List<DisplayFeature>,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,     // Function to nav to detail page
    toggleSelectedEmail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    LaunchedEffect(key1 = contentType) {
        if (contentType == PolitipalContentType.SINGLE_PANE && !homeUIState.isDetailOnlyOpen) {
            closeDetailScreen()
        }
    }

    val emailLazyListState = rememberLazyListState()
    var text by remember { mutableStateOf("") }
    var active by remember { mutableStateOf((false)) }
    var items = remember {
        mutableStateListOf(
            "test1",
            "test2",
            "test3"
        )
    }



    Box(modifier = modifier.fillMaxSize()) {
        // Helpful Tutorial: https://www.composables.com/material3/dockedsearchbar/video
        SearchBar(
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
            items.forEach{
                Row(modifier = Modifier.padding(all = 14.dp)){
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        imageVector = Icons.Default.History,
                        contentDescription = "Hist Icon")
                    Text(text = it)
                }
            }
        }
        // Main Bill Search Screen - Bonnie
        /*
        RepSearchContent(
            homeUIState = homeUIState,
            modifier = Modifier.fillMaxSize(),
            emails = homeUIState.emails,     // Emails for now, bills later
            navigateToDetail = navigateToDetail
        )*/
    }
}

/*
@Composable
fun RepSearchContent(
    homeUIState: homeUIState,
    modifier: Modifier = Modifier,
    emails: List<Email>,  // Will be Bills Soon
    navigateToDetail: (Long, PolitipalContentType) -> Unit
) {
    Box(modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        RepSearchBar(
            emails = emails,
            onSearchItemSelected = { searchedEmail ->
                navigateToDetail(searchedEmail.id, PolitipalContentType.SINGLE_PANE)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)

        )
    }
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp)
    ){

    }

}

*/