

package com.example.politipal.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.DisplayFeature
import com.example.politipal.data.firebaseData.LocalEmailsDataProvider
import com.example.politipal.ui.navigation.ModalNavigationDrawerContent
import com.example.politipal.ui.navigation.PermanentNavigationDrawerContent
import com.example.politipal.ui.navigation.ReplyBottomNavigationBar
import com.example.politipal.ui.navigation.ReplyNavigationActions
import com.example.politipal.ui.navigation.ReplyNavigationRail
import com.example.politipal.ui.navigation.PolitipalRoute
import com.example.politipal.ui.navigation.ReplyTopLevelDestination
import com.example.politipal.ui.theme.AppTheme
import com.example.politipal.ui.utils.PolitipalContentType
import com.example.politipal.ui.utils.PolitipalNavigationContentPosition
import com.example.politipal.ui.utils.PolitipalNavigationType
import kotlinx.coroutines.launch

@Composable
fun PolitipalApp(
    // Parameters
    windowSize: WindowSizeClass,
    displayFeatures: List<DisplayFeature>,
    homeUIState: homeUIState,
    closeDetailScreen: () -> Unit = {},
    navigateToDetail: (Long, PolitipalContentType) -> Unit = { _, _ -> },
    toggleSelectedEmail: (Long) -> Unit = { },
    toggleSelectedRep: (String) -> Unit = { }
) {
    val navigationType: PolitipalNavigationType = PolitipalNavigationType.BOTTOM_NAVIGATION
    val contentType: PolitipalContentType = PolitipalContentType.SINGLE_PANE
    val navigationContentPosition = PolitipalNavigationContentPosition.TOP

    ReplyNavigationWrapper(
        navigationType = navigationType,
        contentType = contentType,
        displayFeatures = displayFeatures,
        navigationContentPosition = navigationContentPosition,
        homeUIState = homeUIState,
        closeDetailScreen = closeDetailScreen,
        navigateToDetail = navigateToDetail,
        toggleSelectedEmail = toggleSelectedEmail,
        toggleSelectedRep = toggleSelectedRep,
        )
}

@Composable
private fun ReplyNavigationWrapper(
    navigationType: PolitipalNavigationType,
    contentType: PolitipalContentType,
    displayFeatures: List<DisplayFeature>,
    navigationContentPosition: PolitipalNavigationContentPosition,
    homeUIState: homeUIState,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    toggleSelectedRep: (String) -> Unit
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val navController = rememberNavController()
    val navigationActions = remember(navController) {
        ReplyNavigationActions(navController)
    }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val selectedDestination =
        navBackStackEntry?.destination?.route ?: PolitipalRoute.SETTINGS

    if (navigationType == PolitipalNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        // TODO check on custom width of PermanentNavigationDrawer: b/232495216
        PermanentNavigationDrawer(drawerContent = {
            PermanentNavigationDrawerContent(
                selectedDestination = selectedDestination,
                navigationContentPosition = navigationContentPosition,
                navigateToTopLevelDestination = navigationActions::navigateTo,
            )
        }) {
            ReplyAppContent(
                navigationType = navigationType,
                contentType = contentType,
                displayFeatures = displayFeatures,
                navigationContentPosition = navigationContentPosition,
                homeUIState = homeUIState,
                navController = navController,
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedEmail = toggleSelectedEmail,
                toggleSelectedRep = toggleSelectedRep


            )
        }
    } else {
        ModalNavigationDrawer(
            drawerContent = {
                ModalNavigationDrawerContent(
                    selectedDestination = selectedDestination,
                    navigationContentPosition = navigationContentPosition,
                    navigateToTopLevelDestination = navigationActions::navigateTo,
                    onDrawerClicked = {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                )
            },
            drawerState = drawerState
        ) {
            ReplyAppContent(
                navigationType = navigationType,
                contentType = contentType,
                displayFeatures = displayFeatures,
                navigationContentPosition = navigationContentPosition,
                homeUIState = homeUIState,
                navController = navController,
                selectedDestination = selectedDestination,
                navigateToTopLevelDestination = navigationActions::navigateTo,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedEmail = toggleSelectedEmail,
                toggleSelectedRep = toggleSelectedRep
            ) {
                scope.launch {
                    drawerState.open()
                }
            }
        }
    }
}

@Composable
fun ReplyAppContent(
    modifier: Modifier = Modifier,
    navigationType: PolitipalNavigationType,
    contentType: PolitipalContentType,
    displayFeatures: List<DisplayFeature>,
    navigationContentPosition: PolitipalNavigationContentPosition,
    homeUIState: homeUIState,
    navController: NavHostController,
    selectedDestination: String,
    navigateToTopLevelDestination: (ReplyTopLevelDestination) -> Unit,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    toggleSelectedRep: (String) -> Unit,
    onDrawerClicked: () -> Unit = {}
) {
    Row(modifier = modifier.fillMaxSize()) {
        AnimatedVisibility(visible = navigationType == PolitipalNavigationType.NAVIGATION_RAIL) {
            ReplyNavigationRail(
                selectedDestination = selectedDestination,
                navigationContentPosition = navigationContentPosition,
                navigateToTopLevelDestination = navigateToTopLevelDestination,
                onDrawerClicked = onDrawerClicked,
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surfaceContainerLow)
        ) {
            ReplyNavHost(
                navController = navController,
                contentType = contentType,
                displayFeatures = displayFeatures,
                homeUIState = homeUIState,
                navigationType = navigationType,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedEmail = toggleSelectedEmail,
                toggleSelectedRep = toggleSelectedRep,
                modifier = Modifier.weight(1f),
            )
            AnimatedVisibility(visible = navigationType == PolitipalNavigationType.BOTTOM_NAVIGATION) {
                ReplyBottomNavigationBar(
                    selectedDestination = selectedDestination,
                    navigateToTopLevelDestination = navigateToTopLevelDestination
                )
            }
        }
    }
}

@Composable
private fun ReplyNavHost(
    navController: NavHostController,
    contentType: PolitipalContentType,          //
    displayFeatures: List<DisplayFeature>,
    homeUIState: homeUIState,               // Set to recylce view, just a behavior spec
    navigationType: PolitipalNavigationType,
    closeDetailScreen: () -> Unit,
    navigateToDetail: (Long, PolitipalContentType) -> Unit,
    toggleSelectedEmail: (Long) -> Unit,
    toggleSelectedRep: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = PolitipalRoute.HOME,
    ) {
        // Not all of these arguments will be needed, they will dwindle / grow for each page as we work on it
        composable(PolitipalRoute.SETTINGS) {
            SettingsPage(
                contentType = contentType,
                homeUIState = homeUIState,
                navigationType = navigationType,
                displayFeatures = displayFeatures,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedEmail = toggleSelectedEmail
            )
        }
        composable(PolitipalRoute.PROFILE) {
            AboutMePage(
                contentType = contentType,
                homeUIState = homeUIState,
                navigationType = navigationType,
                displayFeatures = displayFeatures,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedEmail = toggleSelectedEmail
            )
        }
        composable(PolitipalRoute.BILLS) {
            // Look into giving this tab memory, if on a bills remember the bills, remember if on blank search page etc...
            BillSearch(
                contentType = contentType,
                homeUIState = homeUIState,
                navigationType = navigationType,
                displayFeatures = displayFeatures,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedEmail = toggleSelectedEmail
            )

        }
        composable(PolitipalRoute.REPS) {
            // Look into giving this tab memory, if on a rep remember the rep, remember if on blank search page etc...
            RepSearch(
                contentType = contentType,
                homeUIState = homeUIState,
                navigationType = navigationType,
                displayFeatures = displayFeatures,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedRep = toggleSelectedRep
            )
        }
        composable(PolitipalRoute.HOME) {
            HomeScreen(
                contentType = contentType,
                homeUIState = homeUIState,
                navigationType = navigationType,
                displayFeatures = displayFeatures,
                closeDetailScreen = closeDetailScreen,
                navigateToDetail = navigateToDetail,
                toggleSelectedEmail = toggleSelectedEmail
            )
        }
    }
}

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Preview(showBackground = true)
@Composable
fun PagePreview() {
    AppTheme {
        PolitipalApp(
            homeUIState = homeUIState(emails = LocalEmailsDataProvider.allEmails),
            windowSize = WindowSizeClass.calculateFromSize(DpSize(400.dp, 900.dp)),
            displayFeatures = emptyList(),
        )
    }
}
