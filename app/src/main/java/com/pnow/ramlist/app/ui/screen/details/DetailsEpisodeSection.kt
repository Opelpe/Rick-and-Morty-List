package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pnow.ramlist.app.ui.model.EpisodeInfo
import com.pnow.ramlist.app.ui.screen.previewEpisode1
import com.pnow.ramlist.app.ui.screen.previewEpisode2
import com.pnow.ramlist.app.ui.screen.previewEpisode3
import com.pnow.ramlist.app.ui.state.EpisodeState
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

fun LazyListScope.episodeSection(modifier: Modifier = Modifier, episodeState: EpisodeState, onRetry: () -> Unit) {
    when (episodeState) {
        is EpisodeState.Loading -> item {
            CircularProgressIndicator(
                modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
                    .wrapContentSize(Alignment.Center)
                    .size(32.dp),
                color = CharacterDetailsColors.TextMuted,
                strokeWidth = 3.dp,
            )
        }

        is EpisodeState.Failure ->
            item {
                RetryButtonWithMessage(
                    modifier = modifier,
                    onRetry = onRetry,
                    message = episodeState.error,
                )
            }

        is EpisodeState.Success -> {
            items(items = episodeState.episodes, key = { it.id }) { episode ->
                EpisodeItem(modifier = modifier, episode = episode)
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
private fun EpisodeItem(modifier: Modifier = Modifier, episode: EpisodeInfo) {
    Row(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
            .background(CharacterDetailsColors.CardBackground, RoundedCornerShape(Dimens.CornerRadius8))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        EpisodeInfo(
            episodeName = episode.name,
            episodeDate = episode.date,
        )

        EpisodeNumber(episodeNumber = episode.episodeNumber)
    }
}

@Composable
private fun EpisodeInfo(modifier: Modifier = Modifier, episodeName: String, episodeDate: String) {
    Column(
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        Text(
            text = episodeName,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = episodeDate,
            fontSize = 11.sp,
            color = CharacterDetailsColors.TextMuted,
        )
    }
}

@Composable
private fun EpisodeNumber(modifier: Modifier = Modifier, episodeNumber: String) {
    Text(
        modifier =
        Modifier
            .background(CharacterDetailsColors.EpisodeBadgeBackground, RoundedCornerShape(Dimens.CornerRadius6))
            .padding(horizontal = 8.dp, vertical = 3.dp),
        text = episodeNumber,
        fontSize = 11.sp,
        fontWeight = FontWeight.Medium,
        color = CharacterDetailsColors.TextMuted,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun EpisodesSectionPreview() {
    RickAndMortyTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        ) {
            episodeSection(
                episodeState =
                EpisodeState.Success(
                    episodes =
                    listOf(
                        previewEpisode1,
                        previewEpisode2,
                        previewEpisode3,
                    ),
                ),
                onRetry = {},
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun EpisodesSectionLoadingPreview() {
    RickAndMortyTheme {
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center),
        ) {
            episodeSection(
                episodeState = EpisodeState.Loading,
                onRetry = {},
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun EpisodesSectionErrorPreview() {
    RickAndMortyTheme {
        LazyColumn {
            episodeSection(
                episodeState = EpisodeState.Failure(error = "Something went wrong"),
                onRetry = {},
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun EpisodeItemPreview() {
    RickAndMortyTheme {
        EpisodeItem(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            episode = previewEpisode1,
        )
    }
}
