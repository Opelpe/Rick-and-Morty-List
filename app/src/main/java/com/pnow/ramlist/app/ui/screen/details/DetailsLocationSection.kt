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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.LocationInfo
import com.pnow.ramlist.app.ui.screen.previewDetails
import com.pnow.ramlist.app.ui.screen.previewLocation2
import com.pnow.ramlist.app.ui.state.CharacterInfoState
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun LocationsSection(modifier: Modifier = Modifier, state: CharacterInfoState, onRetry: () -> Unit) {
    when (state) {
        is CharacterInfoState.Loading ->
            CircularProgressIndicator(
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentSize(Alignment.Center)
                    .size(32.dp),
                color = CharacterDetailsColors.TextMuted,
                strokeWidth = 3.dp,
            )

        is CharacterInfoState.Failure ->
            RetryButtonWithMessage(
                modifier = Modifier.padding(vertical = 16.dp),
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
        horizontalArrangement = Arrangement.spacedBy(10.dp),
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
                color = CharacterDetailsColors.CardBackground,
                shape = RoundedCornerShape(Dimens.CornerRadius8),
            )
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
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
            fontSize = 10.sp,
            lineHeight = 10.sp,
            color = CharacterDetailsColors.TextMuted,
            letterSpacing = 0.5.sp,
        )
        Text(
            text = locationName,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.TextPrimary,
            lineHeight = 18.sp,
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
            .padding(top = 3.dp, start = 3.dp),
        verticalArrangement = Arrangement.spacedBy(3.dp),
    ) {
        Text(
            text = "$label: ",
            fontSize = 9.sp,
            lineHeight = 9.sp,
            color = CharacterDetailsColors.TextMuted,
        )
        Text(
            modifier = Modifier.padding(start = 4.dp),
            text = value,
            fontSize = 10.sp,
            lineHeight = 10.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.TextPrimary,
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
                .padding(40.dp),
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
                .padding(vertical = 20.dp),
            state = CharacterInfoState.Failure("Failed to load data, retry!"),
            onRetry = {},
        )
    }
}
