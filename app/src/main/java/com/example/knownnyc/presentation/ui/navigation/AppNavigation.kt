package com.example.knownnyc.presentation.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.knownnyc.presentation.boroughs.BoroughScreen

@Composable
fun AppNavigationGraph() {
    
    val navController : NavHostController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {

        composable(Routes.HOME_SCREEN) {
            BoroughScreen (onBoroughClicked = {borough, title ->

                //TODO: Navigate to Parks Screen

            })
        }

        composable(Routes.PARKS_SCREEN) {

        }
    }
}