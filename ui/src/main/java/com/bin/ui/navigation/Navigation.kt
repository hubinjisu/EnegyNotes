package com.bin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = NotesRoute.route) {
        NotesRoute.composable(this, navController)
        SummaryRoute.composable(this, navController)
        RecordRoute.composable(this, navController)
    }
}
