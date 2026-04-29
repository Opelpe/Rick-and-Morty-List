package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.screen.character.DetailsHeroView
import com.pnow.ramlist.app.ui.screen.previewDetails
import com.pnow.ramlist.app.ui.state.CharacterInfoState
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun DetailsHeroSection(state: CharacterInfoState, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxWidth().background(CharacterDetailsColors.HeroBackground)) {
        when (state) {
            is CharacterInfoState.Loading ->
                Box(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    CircularProgressIndicator(
                        color = CharacterDetailsColors.TextMuted,
                        modifier = Modifier.size(64.dp),
                    )
                }
            is CharacterInfoState.Failure ->
                Box(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 200.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    ErrorMessage(
                        message = state.error,
                        modifier = Modifier.padding(top = 56.dp),
                    )
                }
            is CharacterInfoState.Success ->
                DetailsHeroView(
                    detailsInfo = state.info,
                    modifier = Modifier.fillMaxWidth().padding(top = 20.dp),
                )
        }

        IconButton(
            onClick = onBackClick,
            modifier =
            Modifier
                .align(Alignment.TopStart),
        ) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = stringResource(R.string.navigate_back),
                tint = CharacterDetailsColors.TextPrimary,
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun SuccessStatePreview() {
    RickAndMortyTheme {
        DetailsHeroSection(
            state = CharacterInfoState.Success(previewDetails),
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun LoadingStatePreview() {
    RickAndMortyTheme {
        DetailsHeroSection(
            state = CharacterInfoState.Loading,
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun ErrorStatePreview() {
    RickAndMortyTheme {
        DetailsHeroSection(
            state = CharacterInfoState.Failure("Failed to load character"),
            onBackClick = {},
        )
    }
}
