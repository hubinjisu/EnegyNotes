package com.bin.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = RecordRoute.route) {
        NotesRoute.composable(this, navController)
        RecordRoute.composable(this, navController)
        AboutRoute.composable(this, navController)
    }
}
