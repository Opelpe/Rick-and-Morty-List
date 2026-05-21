package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.LocationInfo
import com.pnow.ramlist.app.ui.screen.previewDetails
import com.pnow.ramlist.app.ui.screen.previewLocation2
import com.pnow.ramlist.app.ui.state.CharacterInfoState
import com.pnow.ramlist.core.ui.dimens.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme
import com.pnow.ramlist.core.ui.theme.detailsColors

@Composable
fun LocationsSection(modifier: Modifier = Modifier, state: CharacterInfoState, onRetry: () -> Unit) {
    when (state) {
        is CharacterInfoState.Loading ->
            CircularProgressIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .size(Dimens.ProgressSizeSmall),
                color = MaterialTheme.detailsColors.textMuted,
                strokeWidth = Dimens.BorderWidth3,
            )

        is CharacterInfoState.Failure ->
            RetryButtonWithMessage(
                modifier = Modifier.padding(vertical = Dimens.Spacing16),
                onRetry = onRetry,
                message = state.error,
            )

        is CharacterInfoState.Success,
        ->
            LocationsRow(
                modifier = modifier,
                origin = state.info.origin,
                location = state.info.location,
            )
    }
}

@Composable
private fun LocationsRow(modifier: Modifier = Modifier, origin: LocationInfo, location: LocationInfo) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Spacing10),
    ) {
        LocationCard(
            badge = stringResource(R.string.common_word_origin),
            location = origin,
            modifier = Modifier.weight(1f),
        )
        LocationCard(
            badge = stringResource(R.string.common_word_location),
            location = location,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun LocationCard(modifier: Modifier = Modifier, badge: String, location: LocationInfo) {
    Column(
        modifier =
        modifier
            .background(
                color = MaterialTheme.detailsColors.cardBackground,
                shape = RoundedCornerShape(Dimens.CornerRadius8),
            )
            .padding(Dimens.Spacing8),
        verticalArrangement = Arrangement.spacedBy(Dimens.Spacing5),
    ) {
        LocationTitle(
            badge = badge,
            locationName = location.name,
        )

        LocationDetails(
            label = stringResource(R.string.common_word_type),
            value = location.type,
        )
        LocationDetails(
            label = stringResource(R.string.common_word_dimension),
            value = location.dimension,
        )
    }
}

@Composable
private fun LocationTitle(modifier: Modifier = Modifier, badge: String, locationName: String) {
    Column(modifier = modifier) {
        Text(
            text = badge.uppercase(),
            fontSize = Dimens.TextSize10,
            lineHeight = Dimens.TextSize10,
            color = MaterialTheme.detailsColors.textMuted,
            letterSpacing = Dimens.LetterSpacingTight,
        )
        Text(
            text = locationName,
            fontSize = Dimens.TextSize13,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.detailsColors.textPrimary,
            lineHeight = Dimens.TextSize18,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun LocationDetails(modifier: Modifier = Modifier, label: String, value: String) {
    Column(
        modifier =
        modifier
            .fillMaxWidth()
            .padding(top = Dimens.Spacing3, start = Dimens.Spacing3),
        verticalArrangement = Arrangement.spacedBy(Dimens.Spacing3),
    ) {
        Text(
            text = "$label: ",
            fontSize = Dimens.TextSize9,
            lineHeight = Dimens.TextSize9,
            color = MaterialTheme.detailsColors.textMuted,
        )
        Text(
            modifier = Modifier.padding(start = Dimens.Spacing4),
            text = value,
            fontSize = Dimens.TextSize10,
            lineHeight = Dimens.TextSize10,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.detailsColors.textPrimary,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun LocationSectionSuccessPreview() {
    RickAndMortyTheme {
        LocationsSection(
            modifier =
            Modifier.fillMaxWidth(),
            state = CharacterInfoState.Success(info = previewDetails),
            onRetry = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun LocationCardPreview() {
    RickAndMortyTheme {
        LocationCard(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(Dimens.Spacing40),
            location = previewLocation2,
            badge = "Location",
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun LocationSectionErroePreview() {
    RickAndMortyTheme {
        LocationsSection(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = Dimens.Spacing20),
            state = CharacterInfoState.Failure("Failed to load data, retry!"),
            onRetry = {},
        )
    }
}
