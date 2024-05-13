
package com.example.politipal.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.data.Email
import com.example.politipal.ui.components.BillSearchBar
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BillSearch(
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
        Box(modifier = modifier.fillMaxSize()) {

        // Main Bill Search Screen - Bonnie
        BillSearchContent(
            homeUIState = homeUIState,
            modifier = Modifier.fillMaxSize(),
            emails =  homeUIState.emails,     // Emails for now, bills later
            navigateToDetail = navigateToDetail
        )
    }
}

@Composable
fun BillSearchContent(
    homeUIState: homeUIState,
    modifier: Modifier = Modifier,
    emails: List<Email>,  // Will be Bills Soon
    navigateToDetail: (Long, PolitipalContentType) -> Unit
) {
    Box(modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)) {
        BillSearchBar(
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



