package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.screen.character.DetailsHeroView
import com.pnow.ramlist.app.ui.screen.previewDetails
import com.pnow.ramlist.app.ui.state.CharacterInfoState
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun DetailsHeroSection(
    modifier: Modifier = Modifier,
    state: CharacterInfoState,
    onBackClick: () -> Unit,
    onRetry: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(CharacterDetailsColors.HeroBackground),
    ) {
        when (state) {
            is CharacterInfoState.Loading ->
                CircularProgressIndicator(
                    color = CharacterDetailsColors.TextMuted,
                    modifier = Modifier
                        .size(Dimens.ProgressSizeLarge)
                        .align(Alignment.Center),
                )

            is CharacterInfoState.Failure ->
                RetryButtonWithMessage(
                    modifier = Modifier.padding(vertical = Dimens.Spacing16),
                    onRetry = onRetry,
                    message = state.error,
                )

            is CharacterInfoState.Success ->
                DetailsHeroView(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.Spacing20),
                    detailsInfo = state.info,
                )
        }

        IconButton(
            modifier = Modifier.align(Alignment.TopEnd),
            onClick = onBackClick,
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
            onRetry = {},
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
            onRetry = {},
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
            onRetry = {},
        )
    }
}
