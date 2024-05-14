package com.example.politipal.ui

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.example.politipal.R
import com.example.politipal.data.Email
import com.example.politipal.data.MailboxType
import com.example.politipal.data.firebaseData.LocalAccountsDataProvider
import com.example.politipal.data.firebaseData.LocalEmailsDataProvider
import com.example.politipal.ui.components.ReplyProfileImage

@Composable
fun ReplyDetailsScreen(
    homeUIState: homeUIState,
    onBackPressed: () -> Unit,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    BackHandler {
        onBackPressed()
    }
    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier
                .testTag(stringResource(R.string.details_screen))
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.inverseOnSurface)
                .padding(top = dimensionResource(R.dimen.detail_card_list_padding_top))
        ) {
            item {
                if (isFullScreen) {
                    ReplyDetailsScreenTopBar(
                        onBackPressed,
                        homeUIState,
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = dimensionResource(R.dimen.detail_topbar_padding_bottom))
                    )
                }
                ReplyEmailDetailsCard(
                    //CHANGE
                    //email = homeUIState.selectedEmails,
                    email = Email(
                        id = 8L,
                        sender = LocalAccountsDataProvider.getContactAccountByUid(13L),
                        recipients = listOf(LocalAccountsDataProvider.getDefaultUserAccount()),
                        subject = "Your update on Google Play Store is live!",
                        body = """
              Your update, 0.1.1, is now live on the Play Store and available for your alpha users to start testing.
              
              Your alpha testers will be automatically notified. If you'd rather send them a link directly, go to your Google Play Console and follow the instructions for obtaining an open alpha testing link.
            """.trimIndent(),
                        mailbox = MailboxType.TRASH,
                        createdAt = "3 hours ago",
                    ),
                    //CHANGE
                    mailboxType = MailboxType.SENT,
                    isFullScreen = isFullScreen,
                    modifier = if (isFullScreen) {
                        Modifier.padding(horizontal = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                    } else {
                        Modifier.padding(end = dimensionResource(R.dimen.detail_card_outer_padding_horizontal))
                    }
                )
            }
        }
    }
}

@Composable
private fun ReplyDetailsScreenTopBar(
    onBackButtonClicked: () -> Unit,
    replyUiState: homeUIState,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = onBackButtonClicked,
            modifier = Modifier
                .padding(horizontal = dimensionResource(R.dimen.detail_topbar_back_button_padding_horizontal))
                .background(MaterialTheme.colorScheme.surface, shape = CircleShape),
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = stringResource(id = R.string.navigation_back)
            )
        }
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = dimensionResource(R.dimen.detail_subject_padding_end))
        ) {
            Text(
                //CHANGE
                text = stringResource(id = R.string.email_0_subject),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun ReplyEmailDetailsCard(
    email: Email,
    mailboxType: MailboxType,
    modifier: Modifier = Modifier,
    isFullScreen: Boolean = false
) {
    val context = LocalContext.current
    val displayToast = { text: String ->
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
    }
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.detail_card_inner_padding))
        ) {
            DetailsScreenHeader(
                email,
                Modifier.fillMaxWidth()
            )
            if (isFullScreen) {
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.detail_content_padding_top)))
            } else {
                Text(
                    //CHANGE
                    text = stringResource(id = R.string.email_0_subject),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.outline,
                    modifier = Modifier.padding(
                        top = dimensionResource(R.dimen.detail_content_padding_top),
                        bottom = dimensionResource(R.dimen.detail_expanded_subject_body_spacing)
                    ),
                )
            }
            Text(
                //CHANGE
                text = stringResource(id = R.string.email_0_body),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            //DetailsScreenButtonBar(mailboxType, displayToast)
        }
    }
}
/*
@Composable
private fun DetailsScreenButtonBar(
    mailboxType: MailboxType,
    Type:
    displayToast: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        when (mailboxType) {
            MailboxType.Settings ->
                ActionButton(
                    text = stringResource(id = R.string.link_to_article),
                    onButtonClicked = displayToast
                )

            MailboxType.Home ->
                ActionButton(
                    text = stringResource(id = R.string.link_to_article),
                    onButtonClicked = displayToast
                )

            MailboxType.Spam ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = dimensionResource(R.dimen.detail_button_bar_padding_vertical)
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.detail_button_bar_item_spacing)
                    ),
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.move_to_inbox),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = stringResource(id = R.string.delete),
                        onButtonClicked = displayToast,
                        containIrreversibleAction = true,
                        modifier = Modifier.weight(1f)
                    )
                }

            MailboxType.Sent, MailboxType.Inbox ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            vertical = dimensionResource(R.dimen.detail_button_bar_padding_vertical)
                        ),
                    horizontalArrangement = Arrangement.spacedBy(
                        dimensionResource(R.dimen.detail_button_bar_item_spacing)
                    ),
                ) {
                    ActionButton(
                        text = stringResource(id = R.string.reply),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                    ActionButton(
                        text = stringResource(id = R.string.request_edit),
                        onButtonClicked = displayToast,
                        modifier = Modifier.weight(1f)
                    )
                }

            MailboxType.INBOX -> TODO()
            MailboxType.DRAFTS -> TODO()
            MailboxType.SENT -> TODO()
            MailboxType.SPAM -> TODO()
            MailboxType.TRASH -> TODO()
        }
    }
}
*/
@Composable
private fun DetailsScreenHeader(email: Email, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        ReplyProfileImage(
            drawableResource = email.sender.avatar,
            //CHANGE
            description = stringResource(id = R.string.account_1_first_name)+ " "
                    + stringResource(id = R.string.account_1_last_name),
            modifier = Modifier.size(
                dimensionResource(R.dimen.email_header_profile_size)
            )
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    horizontal = dimensionResource(R.dimen.email_header_content_padding_horizontal),
                    vertical = dimensionResource(R.dimen.email_header_content_padding_vertical)
                ),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = stringResource(id = R.string.account_1_first_name),
                style = MaterialTheme.typography.labelMedium
            )
            Text(
                text = stringResource(id = R.string.email_0_time),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.outline
            )
        }
    }
}

@Composable
private fun ActionButton(
    text: String,
    onButtonClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    containIrreversibleAction: Boolean = false,
) {
    Box(modifier = modifier) {
        Button(
            onClick = { onButtonClicked(text) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = dimensionResource(R.dimen.detail_action_button_padding_vertical)),
            colors = ButtonDefaults.buttonColors(
                containerColor =
                if (containIrreversibleAction) {
                    MaterialTheme.colorScheme.onErrorContainer
                } else {
                    MaterialTheme.colorScheme.primaryContainer
                }
            )
        ) {
            Text(
                text = text,
                color = if (containIrreversibleAction) {
                    MaterialTheme.colorScheme.onError
                } else {
                    MaterialTheme.colorScheme.onSurfaceVariant
                }
            )
        }
    }
}
