package com.test.subtotal.ui.navigation

import androidx.compose.runtime.Composable
import com.test.subtotal.viewmodels.MainViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.test.subtotal.ui.screens.DetailsScreen
import com.test.subtotal.ui.screens.MainScreen

@Composable
fun RootNavigationGraph(
    navController: NavHostController,
    mainViewModel: MainViewModel,
    startDestination: String
) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = startDestination
    ) {

        composable(route = Graph.HOME) {
            MainScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable(
            route = "${Graph.DETAILS}/{id}",
            //arguments = listOf(navArgument("id") { type = NavType.IntType })
            ) { backStackEntry ->
                val id = backStackEntry.arguments?.getString("id") ?: "-1"
                DetailsScreen(id = id, navController = navController, mainViewModel = mainViewModel)
            }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val HOME = "home_graph"
    const val DETAILS = "details_graph"
}