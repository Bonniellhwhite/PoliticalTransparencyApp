
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Article
import androidx.compose.material.icons.filled.Check
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
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
    modifier: Modifier = Modifier,
    bill: Bill,
) {

    Box(modifier = modifier.fillMaxSize()) {
        // Crystal TODO?
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(all = 50.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        //.weight(1f)
                        .padding(vertical = 15.dp),
                    horizontalArrangement = Arrangement.Start
                ) {
                    Text(
                        text = "${bill.type} ${bill.number}",
                        style = MaterialTheme.typography.bodyLarge,
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
                    .padding(vertical = 45.dp)
                    .padding(20.dp)
            ) {
                // Add your components here
                Text(
                    text = "${bill.originChamber}",
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
                    text = "${bill.updateDate}",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 85.dp)
                    .padding(20.dp)
            ) {
                // Add your components here
                Text(
                    text = "Official link",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                )

            }
            // New Row added here
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 145.dp)
            ) {
                // Add your components here
                Box(
                    modifier = Modifier.weight(1f),
                    content = {
                        LazyColumn(
                            modifier = Modifier.fillMaxHeight(),
                            content = {
                                item {
                                    Text(
                                        text = "Summary: Providing for consideration of the bill (H.R. 6192) to amend the Energy Policy and Conservation Act to prohibit the Secretary of Energy from prescribing any new or amended energy conservation standard for a product that is not technologically feasible and economically justified, and for other purposes; providing for consideration of the bill (H.R. 7109) to require a citizenship question on the decennial census, to require reporting on certain census statistics, and to modify apportionment of Representatives to be based on United States citizens instead of all persons; providing for consideration of the joint resolution (H.J. Res. 109) providing for congressional disapproval under chapter 8 of title 5, United States Code, of the rule submitted by the Securities and Exchange Commission relating to \"Staff Accounting Bulletin No. 121\"; and providing for consideration of the bill (H.R. 2925) to amend the Omnibus Budget Reconciliation Act of 1993 to provide for security of tenure for use of mining claims for ancillary activities, and for other purposes.",
                                        style = MaterialTheme.typography.bodyLarge,
                                        modifier = Modifier.padding(top = 12.dp, bottom = 8.dp),
                                    )
                                }
                            }
                        )
                    }
                )
            }
        }

    }
}
