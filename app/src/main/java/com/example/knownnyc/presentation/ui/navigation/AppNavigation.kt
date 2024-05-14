package com.example.knownnyc.presentation.ui.navigation

import android.os.Bundle
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.knownnyc.R
import com.example.knownnyc.presentation.boroughs.BoroughsScreen
import com.example.knownnyc.presentation.parks.NycParksScreen
import com.example.knownnyc.presentation.parks.NycParksViewModel
import com.example.knownnyc.util.scaffold.AppScaffold
import com.example.knownnyc.presentation.ui.util.scaffold.TitleText

@Composable
fun AppNavigationGraph() {

    val navController: NavHostController = rememberNavController()

    val isBackEnabled: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }


    //title: used by stringResource() to get scaffold topBar title
    val title: MutableIntState = remember {
        mutableIntStateOf(R.string.screen_title_home)
    }

    //titleArgs: used by stringResource() to get Scaffold topBar title
    val titleArgs: MutableState<String> = remember {
        mutableStateOf("")
    }

    val showSearchIcon: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    val searchClicked: MutableState<Boolean> = remember {
        mutableStateOf(false)
    }

    val searchText: MutableState<String> = remember {
        mutableStateOf("")
    }

    navController.addOnDestinationChangedListener { _: NavController, destination: NavDestination, _: Bundle? ->
        Log.d("nav", "nav destination changed to ${destination.route}")

        isBackEnabled.value = !destination.route.equals(Routes.HOME_SCREEN)

        if (destination.route!!.startsWith(Routes.PARKS_SCREEN, startIndex = 0)) {
            title.intValue = R.string.screen_title_parks
            showSearchIcon.value = true
        } else {
            title.intValue = R.string.screen_title_home
            showSearchIcon.value = false
            searchClicked.value = false
        }
    }
    AppScaffold(
        title = {
            if (searchClicked.value) {
                SearchBar(onSearchTextChange = {
                    searchText.value = it
                })
            } else {
                TitleText(title = stringResource(title.intValue, titleArgs.value))
            }
        },
        showBackIcon = isBackEnabled.value,
        onBackClick = {
            if (!searchClicked.value) navController.popBackStack()
            searchClicked.value = !searchClicked.value
        },
        showSearchIcon = showSearchIcon.value && !searchClicked.value,
        onActionClick = {
            searchClicked.value = !searchClicked.value
        },
    ) { paddingValues: PaddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {
                composable(Routes.HOME_SCREEN) {
                    BoroughsScreen(onBoroughClicked = { borough, title ->
                        titleArgs.value = title
                        navController.navigate(Routes.PARKS_SCREEN + "?borough=$borough")
                    })
                }
                composable(
                    Routes.PARKS_SCREEN + "?borough={borough}",
                    arguments = listOf(
                        navArgument("borough") {
                            type = NavType.StringType
                        },
                    ),
                ) { backStackEntry: NavBackStackEntry ->
                    // TODO: PROJECT 2 add PARKSCREEN HERE

                    val boroCode = backStackEntry.arguments?.getString("borough") ?: "Unknown"

                    NycParksScreen(
                      boroCode = boroCode,
                     searchText = searchText.value
                    )
                }
            }
        }
    }
}

@Composable
fun SearchBar(searchText: String = "", onSearchTextChange: (String) -> Unit = {}) {
    TextField(
        value = searchText,
        onValueChange = onSearchTextChange,
        label = { Text("Search for Parks") },
        singleLine = true,
        leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search Icon") },
        modifier = Modifier.fillMaxWidth()
    )
}

