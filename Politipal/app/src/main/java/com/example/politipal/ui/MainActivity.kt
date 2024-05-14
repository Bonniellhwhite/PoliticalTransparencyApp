
package com.example.politipal.ui

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.politipal.data.firebaseData.FirebaseDataRetriever
import com.example.politipal.data.firebaseData.LocalEmailsDataProvider
import com.example.politipal.ui.theme.AppTheme
import com.google.accompanist.adaptive.calculateDisplayFeatures


class MainActivity : ComponentActivity() {

    private val viewModel: ReplyHomeViewModel by viewModels()

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        viewModel.fetchFirebaseReps()
        viewModel.fetchFirebaseBills()

        setContent {
            AppTheme {
                val windowSize = calculateWindowSizeClass(this)
                val displayFeatures = calculateDisplayFeatures(this)
                val uiState by viewModel.uiState.collectAsStateWithLifecycle()

                // Initializes App & passes in screen dimension calculations, UI state
                PolitipalApp(
                    windowSize = windowSize,
                    displayFeatures = displayFeatures,
                    homeUIState = uiState,
                    closeDetailScreen = {
                        viewModel.closeDetailScreen()
                    },
                    navigateToDetail = { emailId, pane ->
                        viewModel.setOpenedEmail(emailId, pane)
                    },
                    toggleSelectedEmail = { emailId ->
                        viewModel.toggleSelectedEmail(emailId)
                    },
                    toggleSelectedRep = { repId ->
                        viewModel.toggleSelectedRep(repId)
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppTheme {
        PolitipalApp(
            homeUIState = homeUIState(emails = LocalEmailsDataProvider.allEmails),
            windowSize = WindowSizeClass.calculateFromSize(DpSize(400.dp, 900.dp)),
            displayFeatures = emptyList(),
        )
    }
}
