package com.pnow.rick_and_morty_list.app.ui.view

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import com.pnow.rick_and_morty_list.app.ui.viewmodel.CharacterViewModel

@Composable
fun CharactersListScreen(
    viewModel: CharacterViewModel = hiltViewModel(),
    onCharacterClick: (CharacterUIModel) -> Unit
) {
    val charactersFlow = viewModel.charactersState.collectAsState(initial = null)

    charactersFlow.value?.let { pagingData ->
        val characters = pagingData.collectAsLazyPagingItems()

        LazyColumn {
            items(characters.itemCount) { index ->
                val character = characters[index]
                if (character != null) {
                    CharacterListItem(character = character, onClick = { onCharacterClick(character) })
                }
            }

            characters.apply {
                when (characters) {
                    loadState.refresh is LoadState.Loading -> item {
                        CircularProgressIndicator(Modifier.padding(16.dp))
                    }

                    loadState.append is LoadState.Loading -> item {
                        CircularProgressIndicator(Modifier.padding(16.dp))
                    }

                    loadState.refresh is LoadState.Error -> item {
                        Text("Error loading data", color = Color.Red, modifier = Modifier.padding(16.dp))
                    }
                }
            }
        }
    }
}

