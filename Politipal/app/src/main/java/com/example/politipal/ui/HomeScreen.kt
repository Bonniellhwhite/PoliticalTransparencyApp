
package com.example.politipal.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.systemBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.window.layout.DisplayFeature
import com.example.politipal.R
import com.example.politipal.data.Email
import com.example.politipal.ui.components.EmailDetailAppBar
import com.example.politipal.ui.components.ReplyEmailThreadItem
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType
import java.util.Calendar


// Animation: https://developer.android.com/quick-guides/content/video/animation-in-compose?hl=en


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
                Text(text = "Today's Updates!",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.padding(start = 15.dp)
                )
            }
            item{

                val calendar = Calendar.getInstance()
                val year = calendar.get(Calendar.YEAR)
                val month = calendar.get(Calendar.MONTH) + 1 // Month is zero-based, so add 1
                val day = calendar.get(Calendar.DAY_OF_MONTH)
                val dateText = "$month/$day/$year" // Change format as needed

                Text(text = dateText,
                    style = MaterialTheme.typography.titleSmall,
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    fontFamily = FontFamily.Default,
                    modifier = Modifier.padding(start = 25.dp)
                )

                Spacer(modifier = Modifier.height(5.dp))
            }
            item {
                    Card(
                        modifier = Modifier
                            .padding(15.dp)
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)) // Light background
                    ) {
                        Image(painter = painterResource(id = R.drawable.graph),
                            contentDescription = "rep m",
                            modifier = Modifier
                                .clip(RoundedCornerShape(10.dp))
                                .padding(all = 10.dp)
                                .fillMaxSize()
                                .height(200.dp)
                                .align(Alignment.CenterHorizontally)
                        )


                }
            }

            item {
                Row (modifier = Modifier.padding(end = 15.dp)){
                    Card(
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .wrapContentHeight()
                            .weight(1f),
                        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "42",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(text = "adds")

                        }

                    }
                    Card(
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .wrapContentHeight()
                            .weight(1f),
                        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "378",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(text = "Reps")

                        }

                    }
                    Card(
                        modifier = Modifier
                            .padding(start = 15.dp)
                            .wrapContentHeight()
                            .weight(1f),
                        shape = RoundedCornerShape(12.dp), // Rounded corners for a modern look
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(10.dp)
                                .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                text = "50",
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                style = MaterialTheme.typography.headlineSmall
                            )
                            Text(text = "Bills")
                    }
                }
            }
                //Spacer(modifier = Modifier.height(30.dp))
            }


            item{
                    Text(text = "Try Out The Tool",
                        style = MaterialTheme.typography.titleLarge,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Serif,
                        modifier = Modifier.padding(start = 15.dp, top = 10.dp, bottom = 10.dp)
                            .align(Alignment.Center)
                            .fillMaxWidth()
                    )


            }
            /*
            item{
                Card(modifier =  Modifier.wrapContentWidth()
                    .padding(all = 10.dp)){
                    Box(
                        modifier = Modifier.padding(10.dp).height(100.dp)
                    ){
                        Text(text = "Today's Updates!",
                            style = MaterialTheme.typography.titleLarge,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Serif,
                            modifier = Modifier.padding(start = 15.dp).fillMaxWidth()
                        )
                    }
                }

            } */

        }
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
            .padding(15.dp),
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