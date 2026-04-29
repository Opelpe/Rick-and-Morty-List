package com.pnow.ramlist.app.ui.screen.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.collectAsLazyPagingItems
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.viewmodel.CharacterViewModel

@Composable
fun CharacterListScreen(
    onCharacterClick: (id: Int) -> Unit = {},
    viewModel: CharacterViewModel = hiltViewModel(),
    modifier: Modifier = Modifier,
) {
    val characters = viewModel.charactersState.collectAsLazyPagingItems()

    Box(
        modifier =
        modifier
            .fillMaxSize()
            .systemBarsPadding()
            .background(MaterialTheme.colorScheme.background),
    ) {
        CharacterListBackground(
            modifier = Modifier.fillMaxSize(),
        )

        Column(modifier = Modifier.fillMaxSize()) {
            CharacterListHeader(modifier = Modifier.fillMaxWidth())
            CharactersColumn(
                characters = characters,
                onCharacterClick = onCharacterClick,
                modifier = Modifier.fillMaxSize(),
            )
        }
    }
}

@Composable
private fun CharacterListBackground(modifier: Modifier = Modifier) {
    Image(
        modifier = modifier,
        painter = painterResource(id = R.drawable.ic_rick_morty_transparent_logo),
        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
        contentDescription = null,
    )
}