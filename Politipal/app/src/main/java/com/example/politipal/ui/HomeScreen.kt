
package com.example.politipal.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.KeyboardDoubleArrowLeft
import androidx.compose.material.icons.filled.People
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.R
import com.example.politipal.data.Email
import com.example.politipal.ui.components.EmailDetailAppBar
import com.example.politipal.ui.components.ReplyEmailListItem
import com.example.politipal.ui.components.ReplyEmailThreadItem
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType
import com.example.politipal.ui.theme.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    contentType: PolitipalContentType,
    homeUIState: homeUIState,
    navigationType: PolitipalNavigationType,
    displayFeatures: List<DisplayFeature>,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    /**
     * When moving from LIST_AND_DETAIL page to LIST page clear the selection and user should see LIST screen.
     */
    LaunchedEffect(key1 = contentType) {
        if (contentType == PolitipalContentType.SINGLE_PANE && !homeUIState.isDetailOnlyOpen) {
            closeDetailScreen()
        }
    }

        val emailLazyListState = rememberLazyListState()
        Box(modifier = modifier.fillMaxSize()) {


            ReplySinglePaneContent(
                homeUIState = homeUIState,
                toggleEmailSelection = toggleSelectedEmail,
                emailLazyListState = emailLazyListState,
                modifier = Modifier.fillMaxSize(),
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail
            )
        }
    }


@Composable
fun ReplySinglePaneContent(
    homeUIState: homeUIState,
    toggleEmailSelection: (Long) -> Unit,
    emailLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit
) {
    Box(modifier = Modifier.background(Color(0xFF0D0F3E))){
        if (homeUIState.openedEmail != null && homeUIState.isDetailOnlyOpen) {
            BackHandler {
                closeDetailScreen()
            }
            ReplyEmailDetail(email = homeUIState.openedEmail) {
                closeDetailScreen()
            }
        } else {

            ReplyEmailList(
                emails = homeUIState.emails,
                openedEmail = homeUIState.openedEmail,
                selectedEmailIds = homeUIState.selectedEmails,
                toggleEmailSelection = toggleEmailSelection,
                emailLazyListState = emailLazyListState,
                modifier = modifier,
                navigateToDetail = navigateToDetail
            )
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ReplyEmailList(
    emails: List<Email>,
    openedEmail: Email?,
    selectedEmailIds: Set<Long>,
    toggleEmailSelection: (Long) -> Unit,
    emailLazyListState: LazyListState,
    modifier: Modifier = Modifier,
    navigateToDetail: (Long, PolitipalContentType) -> Unit
) {
    Box(modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)) {
       /* ReplyDockedSearchBar(
            emails = emails,
            onSearchItemSelected = { searchedEmail ->
                navigateToDetail(searchedEmail.id, ReplyContentType.SINGLE_PANE)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
        )*/
        LazyColumn(
            modifier = modifier
                .fillMaxWidth(),
            state = emailLazyListState
        ) {

            item {
                WelcomeSection()
                Spacer(modifier = Modifier.height(40.dp))

            }
            item{
                Text(text = "April, 30th",
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Default
                )
                Button(onClick = {getReps()}) {
                    Text("test")
                }
                Spacer(modifier = Modifier.height(5.dp))
            }
            item{
                Text(text = "Today's Updates!",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif
                )
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                Row{
                    ModernCard(title = "Test 1", subtitle = "Test Subtitle", description = "Test")
                    //ModernCard(title = "Test 1", subtitle = "Test Subtitle", description = "Test")
                }
            }
            item{
                ModernCard(title = "Test 1", subtitle = "Test Subtitle", description = "Test")
            }
            item{
                ModernCard(title = "Test 1", subtitle = "Test Subtitle", description = "Test")
            }
            item{
                ModernCard(title = "Test 1", subtitle = "Test Subtitle", description = "Test")
            }
            item{
                ModernCard(title = "Test 1", subtitle = "Test Subtitle", description = "Test")
            }
            /*
            items(items = emails, key = { it.id }) { email ->

                ReplyEmailListItem(
                    email = email,
                    navigateToDetail = { emailId ->
                        navigateToDetail(emailId, PolitipalContentType.SINGLE_PANE)
                    },
                    toggleSelection = toggleEmailSelection,
                    isOpened = openedEmail?.id == email.id,
                    isSelected = selectedEmailIds.contains(email.id)
                )
            } */
            // Add extra spacing at the bottom if
            item {
                Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
            }
        }
    }
}
fun getReps(){

    Log.d(TAG,"Button Test Click")
    val db = Firebase.firestore
    db.collection("reps")
        .get()
        .addOnSuccessListener { result ->
            Log.d(TAG,"Got Data")
            val firstDocument = result.documents.first() // Get the first document
            Log.d(TAG, "First document ID: ${firstDocument.id}")
        }
        .addOnFailureListener { exception ->
            Log.w(TAG, "Error getting documents.", exception)
        }
}
@Composable
fun WelcomeSection(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .paint(painterResource(id = R.drawable.sunset), contentScale = ContentScale.FillWidth)
    ) {
        Column(modifier = Modifier
            .fillMaxWidth()
            .padding(all = 15.dp), // Add this line to make Column fill the Box
            horizontalAlignment = Alignment.Start
                ){

            Spacer(modifier = Modifier.height(60.dp))
            Text(text = "Welcome!",
                style = MaterialTheme.typography.displayLarge,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )

            /*
            Spacer(modifier = Modifier.height(200.dp))
            ExtendedFloatingActionButton(
                text = { Text("Find Representatives") },
                onClick = { /* TODO: Add your click handling logic here */ },
                icon = { Icon(Icons.Default.People, contentDescription = "Left Arrow") }
            )
            Spacer(modifier = Modifier.height(24.dp))
            ExtendedFloatingActionButton(
                text = { Text("         Find Bills        ") },
                onClick = { /* TODO: Add your click handling logic here */ },
                icon = { Icon(Icons.Default.Article, contentDescription = "Left Arrow") }
            )
            Spacer(modifier = Modifier.height(24.dp))

            ExtendedFloatingActionButton(
                text = { Text("Learn more about you!") },
                onClick = { /* TODO: Add your click handling logic here */ },
                icon = { Icon(Icons.Default.KeyboardDoubleArrowLeft, contentDescription = "Left Arrow")
                }
            )



            */
        }
    }
}

@Composable
fun ReplyEmailDetail(
    email: Email,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = true,
    onBackPressed: () -> Unit = {}
) {
    LazyColumn(
        modifier = modifier
            .background(MaterialTheme.colorScheme.inverseOnSurface)
    ) {
        item {
            EmailDetailAppBar(email, isFullScreen) {
                onBackPressed()
            }
        }
        items(items = email.threads, key = { it.id }) { email ->
            ReplyEmailThreadItem(email = email)
        }
        item {
            Spacer(Modifier.windowInsetsBottomHeight(WindowInsets.systemBars))
        }
    }
}

@Composable
fun ModernCard(title: String, subtitle: String, description: String) {
    Card(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)) // Light background
    ) {
        Column(
            modifier = Modifier.padding(16.dp) // Padding inside the card
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineSmall, // Title styling
                color = Color.Black
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.titleMedium, // Subtitle styling
                color = Color.DarkGray,
                modifier = Modifier.padding(top = 4.dp) // Space between title and subtitle
            )

            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium, // Description styling
                color = Color.Gray,
                modifier = Modifier.padding(top = 8.dp) // Space between subtitle and description
            )
        }
    }
}