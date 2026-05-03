package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.pnow.ramlist.app.ui.state.EpisodeState
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

fun LazyListScope.episodeSection(episodeState: EpisodeState) {
    when (episodeState) {
        is EpisodeState.Loading -> item { EpisodesLoadingIndicator() }
        is EpisodeState.Failure ->
            item {
                Box(
                    modifier =
                    Modifier
                        .fillMaxWidth()
                        .defaultMinSize(minHeight = 100.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    ErrorMessage(
                        message = episodeState.error,
                        modifier = Modifier.padding(top = 56.dp),
                    )
                }
            }
        is EpisodeState.Success -> {
            items(items = episodeState.episodes, key = { it.id }) { episode ->
                EpisodeItem(episode = episode)
            }
            item { Spacer(modifier = Modifier.height(20.dp)) }
        }
    }
}

@Composable
private fun EpisodeItem(episode: EpisodeInfo, modifier: Modifier = Modifier) {
    Row(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .padding(bottom = 8.dp)
            .background(CharacterDetailsColors.CardBackground, RoundedCornerShape(Dimens.CornerRadius8))
            .padding(horizontal = 14.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = episode.name,
                fontSize = 13.sp,
                fontWeight = FontWeight.Medium,
                color = CharacterDetailsColors.TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = episode.date,
                fontSize = 11.sp,
                color = CharacterDetailsColors.TextMuted,
            )
        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(
            text = episode.episodeNumber,
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.TextMuted,
            modifier =
            Modifier
                .background(CharacterDetailsColors.EpisodeBadgeBackground, RoundedCornerShape(Dimens.CornerRadius6))
                .padding(horizontal = 8.dp, vertical = 3.dp),
        )
    }
}

@Composable
private fun EpisodesLoadingIndicator() {
    // todo po co box? jest kilka takich miejsc ale już nie będę o tym pisał
    Box(
        modifier =
        Modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 120.dp),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(64.dp),
            color = CharacterDetailsColors.TextMuted,
            strokeWidth = 3.dp,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun EpisodesSectionPreview() {
    RickAndMortyTheme {
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            episodeSection(
                episodeState =
                EpisodeState.Success(
                    episodes =
                    listOf(
                        EpisodeInfo(
                            id = 1,
                            name = "Pilot",
                            episodeNumber = "S01E01",
                            date = "December 2, 2013",
                        ),
                        EpisodeInfo(
                            id = 2,
                            name = "Lawnmower Dog",
                            episodeNumber = "S01E02",
                            date = "December 4, 2018",
                        ),
                        EpisodeInfo(
                            id = 3,
                            name = "Rick And Morty 09",
                            episodeNumber = "S01E02",
                            date = "December 25, 2022",
                        ),
                    ),
                ),
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun EpisodesSectionLoadingPreview() {
    RickAndMortyTheme {
        LazyColumn {
            episodeSection(
                episodeState = EpisodeState.Loading,
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
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun EpisodeItemPreview() {
    RickAndMortyTheme {
        EpisodeItem(
            episode =
            EpisodeInfo(
                id = 2,
                name = "Lawnmower Dog",
                episodeNumber = "S01E02",
                date = "December 9, 2013",
            ),
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
        )
    }
}
