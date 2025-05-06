package com.pnow.rick_and_morty_list.app.ui

import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import com.pnow.rick_and_morty_list.app.ui.view.CharacterDetailsScreen
import com.pnow.rick_and_morty_list.app.ui.view.CharactersListScreen
import com.pnow.rick_and_morty_list.app.ui.viewmodel.DetailsViewModel

sealed class Screen(val route: String) {
    object CharacterList : Screen("character_list")
    object CharacterDetails : Screen("character_details")
}

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.CharacterList.route
) {
    NavHost(navController = navController, startDestination = startDestination) {

        composable(Screen.CharacterList.route) {
            CharactersListScreen(
                onCharacterClick = { character ->
                    val json = Uri.encode(Gson().toJson(character))
                    navController.navigate("${Screen.CharacterDetails.route}/$json")
                }
            )
        }

        composable(
            route = "${Screen.CharacterDetails.route}/{characterJson}",
            arguments = listOf(navArgument("characterJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val characterJson = backStackEntry.arguments?.getString("characterJson")
            val character = Gson().fromJson(characterJson, CharacterUIModel::class.java)

            val viewModel: DetailsViewModel = hiltViewModel()
            val state by viewModel.detailsState.collectAsState()

            CharacterDetailsScreen(
                character = character,
                detailsState = state,
                onFetchDetails = {
                    viewModel.fetchDetails(
                        character.episodeUrl,
                        character.location?.url,
                        character.origin?.url
                    )
                }
            )
        }
    }
}


