
package com.example.politipal.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Switch
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.window.layout.DisplayFeature
import com.example.politipal.R
import com.example.politipal.data.Account
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsPage(
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

    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.White)) {
        // Leslie TODO
            var notify = "Notifications"
            var name = "Name"
            var email = "Email"
            var checked by remember { mutableStateOf(true) }
            Column {

                TopAppBar(title = {
                    Text("Account Settings")
                })

                Row (modifier = Modifier.padding(horizontal = 150.dp) ) {
                    Image(
                        painterResource(id = R.drawable.profile_pic_491a),
                        //contentDescription = "Profile Picture",
                        contentDescription = stringResource(id = R.string.profile),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(90.dp)
                            .clip(CircleShape)
                    )
                }

                /*
                ReplyProfileImage(
                    drawableResource = Account.avatar,
                    description = stringResource(id = R.string.profile),
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.profile_pic_491a))
                )*/

                Row (verticalAlignment = Alignment.CenterVertically){
                    Text(
                        text = notify,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 21.sp,
                        modifier = modifier.padding(horizontal = 20.dp, vertical = 40.dp)
                    )

                    Switch(
                        checked = checked,
                        onCheckedChange = { checked = it }
                    )
                }
                Column {
                    Text(
                        text = name,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 21.sp,
                        modifier = modifier.padding(horizontal = 20.dp)
                    )
                    Box {
                        Image(
                            painterResource(id = R.drawable.drawing1),
                            //contentDescription = "Profile Picture",
                            contentDescription = stringResource(id = R.string.empty),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = "Account Name",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 21.sp,
                            color = Color(0xFFA891D1),
                            modifier = modifier
                                .padding(horizontal = 22.dp, vertical = 25.dp)
                                .background(Color.Transparent)
                        )
                    }
                    Text(
                        text = email,
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 21.sp,
                        modifier = modifier.padding(horizontal = 20.dp)
                    )
                    Box {
                        Image(
                            painterResource(id = R.drawable.drawing1),
                            //contentDescription = "Profile Picture",
                            contentDescription = stringResource(id = R.string.empty),
                            contentScale = ContentScale.FillWidth,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Text(
                            text = "user@example.com",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 21.sp,
                            color = Color(0xFFA891D1),
                            modifier = modifier
                                .padding(horizontal = 22.dp, vertical = 25.dp)
                                .background(Color.Transparent)
                        )
                    }
                }
            }
    }
}