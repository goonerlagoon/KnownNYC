package com.example.knownnyc.presentation.ui.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun AppNavigationGraph() {
    
    val navController : NavHostController = rememberNavController()
    
    NavHost(navController = navController, startDestination = Routes.HOME_SCREEN) {

        composable(Routes.HOME_SCREEN) {
            Text(text = "hi")
        }

        composable(Routes.PARKS_SCREEN) {

        }
    }
}