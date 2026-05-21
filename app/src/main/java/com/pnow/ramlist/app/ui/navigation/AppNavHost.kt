package com.pnow.ramlist.app.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pnow.ramlist.app.ui.navigation.AppDestinations.CHARACTER_DETAILS_ROUTE
import com.pnow.ramlist.app.ui.navigation.AppDestinations.CHARACTER_ID_KEY
import com.pnow.ramlist.app.ui.navigation.AppDestinations.CHARACTER_LIST
import com.pnow.ramlist.app.ui.screen.details.CharacterDetailsScreen
import com.pnow.ramlist.app.ui.screen.list.CharacterListScreen

@Composable
fun AppNavHost(navController: NavHostController = rememberNavController(), modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        startDestination = CHARACTER_LIST,
        modifier = modifier,
    ) {
        composable(CHARACTER_LIST) {
            CharacterListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate(AppDestinations.characterDetails(characterId))
                },
                modifier = Modifier.fillMaxSize(),
            )
        }

        composable(
            route = CHARACTER_DETAILS_ROUTE,
            arguments = listOf(navArgument(CHARACTER_ID_KEY) { type = NavType.IntType }),
        ) {
            CharacterDetailsScreen(
                onBackClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}
