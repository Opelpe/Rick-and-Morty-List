package com.pnow.ramlist.app.ui.screen.list

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.pnow.domain.model.CharacterStatus
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.CharacterInfo
import com.pnow.ramlist.app.ui.screen.character.ListItemHeroView
import com.pnow.ramlist.core.ui.theme.RaMColor
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun CharactersColumn(
    characters: LazyPagingItems<CharacterInfo>,
    modifier: Modifier = Modifier,
    onCharacterClick: (id: Int) -> Unit = {},
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 15.dp, vertical = 3.dp),
        verticalArrangement = Arrangement.spacedBy(6.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(characters.itemCount) { index ->
            characters[index]?.let {
                CharacterListItem(
                    data = it,
                    onClick = onCharacterClick,
                )
            }
        }

        when (characters.loadState.append) {
            is LoadState.Loading -> {
                item { CircularProgressIndicator(modifier = Modifier.padding(16.dp)) }
            }
            is LoadState.Error -> {
                item {
                    Text(
                        text = stringResource(R.string.list_error_loading_more),
                        modifier = Modifier.padding(16.dp),
                    )
                }
            }
            else -> {}
        }
    }
}

@Composable
fun CharacterListItem(
    data: CharacterInfo,
    modifier: Modifier = Modifier,
    isSelected: Boolean = false,
    onClick: (id: Int) -> Unit = {},
) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val borderColor =
        getItemBorderColor(
            darkTheme = isSystemInDarkTheme(),
            isSelected = isSelected,
            isPressed = isPressed,
        )

    val backgroundColor =
        getItemBackgroundColor(
            darkTheme = isSystemInDarkTheme(),
            isSelected = isSelected,
            isPressed = isPressed,
        )

    val borderShape = RoundedCornerShape(8.dp)

    Row(
        modifier =
        modifier
            .border(
                width = 3.dp,
                color = borderColor,
                shape = borderShape,
            )
            .clip(shape = borderShape)
            .clickable(
                interactionSource = interactionSource,
                indication = ripple(bounded = true),
            ) { onClick(data.id) }
            .background(backgroundColor)
            .padding(horizontal = 12.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ListItemHeroView(
            modifier = Modifier.fillMaxWidth(),
            characterInfo = data,
        )
    }
}

private fun getItemBackgroundColor(darkTheme: Boolean, isSelected: Boolean, isPressed: Boolean): Color = when {
    isSelected || isPressed -> if (darkTheme) RaMColor.GreenTransparent else RaMColor.FireTransparent
    else -> if (darkTheme) RaMColor.FireTransparent else RaMColor.GreyTransparent
}

private fun getItemBorderColor(darkTheme: Boolean, isSelected: Boolean, isPressed: Boolean): Color = when {
    isSelected || isPressed -> if (darkTheme) RaMColor.FireDark else RaMColor.Red
    else -> if (darkTheme) RaMColor.GreenDark else RaMColor.FireDark
}

@Composable
@Preview
private fun CharacterListItemPreview() {
    RickAndMortyTheme(darkTheme = true) {
        CharacterListItem(
            data =
            CharacterInfo(
                id = 1,
                name = "Rick Sanchez bardzo dlugieeeeeee sadasddasd",
                status = CharacterStatus.ALIVE,
                statusDescription = "Alive",
                species = "Human",
                gender = "Male",
                imageUrl = "",
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Composable
@Preview(showBackground = true)
private fun CharactersColumnPreview() {
    val items =
        listOf(
            CharacterInfo(
                id = 1,
                name = "Rick Sanchez bardzo dlugieeeeeee sadasddasd",
                status = CharacterStatus.ALIVE,
                statusDescription = "Alive",
                species = "Human",
                gender = "Male",
                imageUrl = "",
            ),
            CharacterInfo(
                id = 2,
                name = "Morty Smith",
                status = CharacterStatus.DEAD,
                statusDescription = "Dead",
                species = "Human",
                gender = "Male",
                imageUrl = "",
            ),
            CharacterInfo(
                id = 3,
                name = "Rick Sanchezzzzzzz",
                status = CharacterStatus.UNKNOWN,
                statusDescription = "Unknown",
                species = "Human",
                gender = "Male",
                imageUrl = "",
            ),
        )
    val pagingItems = flowOf(PagingData.from(items)).collectAsLazyPagingItems()

    RickAndMortyTheme(darkTheme = true) {
        CharactersColumn(characters = pagingItems, modifier = Modifier.fillMaxSize())
    }
}
