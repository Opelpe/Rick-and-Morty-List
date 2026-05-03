package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.screen.previewDetails
import com.pnow.ramlist.app.ui.screen.previewEpisode1
import com.pnow.ramlist.app.ui.screen.previewEpisode2
import com.pnow.ramlist.app.ui.state.CharacterInfoState
import com.pnow.ramlist.app.ui.state.DetailsUiState
import com.pnow.ramlist.app.ui.state.EpisodeState
import com.pnow.ramlist.app.ui.viewmodel.DetailsViewModel
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun CharacterDetailsScreen(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.detailsState.collectAsStateWithLifecycle()
    CharacterDetailsContent(
        state = state,
        onBackClick = onBackClick,
        modifier = modifier,
    )
}

@Composable
private fun CharacterDetailsContent(state: DetailsUiState, onBackClick: () -> Unit, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier =
        modifier
            .fillMaxSize()
            .background(CharacterDetailsColors.Background),
    ) {
        item {
            DetailsHeroSection(
                state = state.character,
                onBackClick = onBackClick,
            )
        }

        item {
            SectionHeader(
                title = stringResource(R.string.common_word_locations),
                modifier =
                Modifier.padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 25.dp,
                ),
            )
        }

        item {
            LocationsSection(
                state = state.character,
                modifier = Modifier.defaultMinSize(minHeight = 120.dp),
            )
        }

        item {
            SectionHeader(
                title = stringResource(R.string.common_word_episodes),
                modifier =
                Modifier.padding(
                    horizontal = 20.dp,
                    vertical = 16.dp,
                ),
            )
        }

        episodeSection(state.episodes)
    }
}

@Composable
private fun SectionHeader(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title.uppercase(),
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        color = CharacterDetailsColors.TextMuted,
        letterSpacing = 1.sp,
        modifier = modifier,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun SuccessStatePreview() {
    RickAndMortyTheme {
        CharacterDetailsContent(
            state =
            DetailsUiState(
                character = CharacterInfoState.Success(previewDetails),
                episodes =
                EpisodeState.Success(
                    listOf(
                        previewEpisode1,
                        previewEpisode2,
                    ),
                ),
            ),
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun LoadingStatePreview() {
    RickAndMortyTheme {
        CharacterDetailsContent(
            state = DetailsUiState(),
            onBackClick = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun ErrorStatePreview() {
    RickAndMortyTheme {
        CharacterDetailsContent(
            state =
            DetailsUiState(
                character = CharacterInfoState.Failure("Failed to load character"),
                episodes = EpisodeState.Failure("Failed to load episodes"),
            ),
            onBackClick = {},
        )
    }
}
