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
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun CharacterDetailsScreen(
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    viewModel: DetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.detailsState.collectAsStateWithLifecycle()
    CharacterDetailsContent(
        modifier = modifier,
        state = state,
        onBackClick = onBackClick,
        onRetry = viewModel::reloadDetails,
    )
}

@Composable
private fun CharacterDetailsContent(
    modifier: Modifier = Modifier,
    state: DetailsUiState,
    onBackClick: () -> Unit,
    onRetry: () -> Unit,
) {
    LazyColumn(
        modifier =
        modifier
            .fillMaxSize()
            .background(CharacterDetailsColors.Background),
    ) {
        item {
            DetailsHeroSection(
                modifier = Modifier.defaultMinSize(minHeight = Dimens.HeroSectionMinHeight),
                state = state.character,
                onBackClick = onBackClick,
                onRetry = onRetry,
            )
        }

        item {
            SectionHeader(
                title = stringResource(R.string.common_word_locations),
                modifier =
                Modifier.padding(
                    start = Dimens.Spacing20,
                    end = Dimens.Spacing20,
                    top = Dimens.Spacing25,
                ),
            )
        }

        item {
            LocationsSection(
                modifier = Modifier
                    .defaultMinSize(minHeight = Dimens.LocationsSectionMinHeight)
                    .padding(horizontal = Dimens.Spacing20),
                state = state.character,
                onRetry = onRetry,
            )
        }

        item {
            SectionHeader(
                modifier =
                Modifier.padding(
                    horizontal = Dimens.Spacing20,
                    vertical = Dimens.Spacing16,
                ),
                title = stringResource(R.string.common_word_episodes),
            )
        }

        episodeSection(
            modifier = Modifier.padding(horizontal = Dimens.Spacing20),
            episodeState = state.episodes,
            onRetry = onRetry,
        )
    }
}

@Composable
private fun SectionHeader(modifier: Modifier = Modifier, title: String) {
    Text(
        modifier = modifier,
        text = title.uppercase(),
        fontSize = Dimens.TextSize11,
        fontWeight = FontWeight.Medium,
        color = CharacterDetailsColors.TextMuted,
        letterSpacing = Dimens.LetterSpacingWide,
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
            onRetry = {},
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
            onRetry = {},
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
                character = CharacterInfoState.Failure("Failed to load character. Tap to retry."),
                episodes = EpisodeState.Failure("Failed to load episodes. Tap to retry."),
            ),
            onBackClick = {},
            onRetry = {},
        )
    }
}
