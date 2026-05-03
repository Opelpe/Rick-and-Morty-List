package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun LocationsSection(state: CharacterInfoState, modifier: Modifier = Modifier) {
    when (state) {
        is CharacterInfoState.Loading ->
//todo na pewno ten box jest potrzebny?
            Box(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(32.dp),
                    color = CharacterDetailsColors.TextMuted,
                    strokeWidth = 3.dp,
                )
            }
//todo reload danych nie byłby lepszy?
        is CharacterInfoState.Failure ->
            Spacer(
                modifier =
                Modifier
                    .fillMaxWidth()
                    .height(120.dp),
            )
        is CharacterInfoState.Success ->
            LocationsRow(
                origin = state.info.origin,
                location = state.info.location,
                modifier = modifier,
            )
    }
}

@Composable
private fun LocationsRow(origin: LocationInfo, location: LocationInfo, modifier: Modifier = Modifier) {
//todo po co ta kolumna?
    Column(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
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
}

@Composable
private fun LocationCard(badge: String, location: LocationInfo, modifier: Modifier = Modifier) {
    Column(
        modifier =
        modifier
            .background(CharacterDetailsColors.CardBackground, RoundedCornerShape(Dimens.CornerRadius8))
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Text(
            text = badge.uppercase(),
            fontSize = 10.sp,
            lineHeight = 10.sp,
            color = CharacterDetailsColors.TextMuted,
            letterSpacing = 0.5.sp,
        )
        Text(
            text = location.name,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.TextPrimary,
            lineHeight = 18.sp,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
        LocationDetailColumn(label = stringResource(R.string.common_word_type), value = location.type)
        LocationDetailColumn(label = stringResource(R.string.common_word_dimension), value = location.dimension)
    }
}

@Composable
private fun LocationDetailColumn(label: String, value: String, modifier: Modifier = Modifier) {
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
private fun LocationSectionPreview() {
    RickAndMortyTheme {
        LocationsSection(
            state = CharacterInfoState.Success(previewDetails),
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
@Composable
private fun LocationCardPreview() {
    RickAndMortyTheme {
        LocationCard(
            location = previewLocation2,
            badge = "Location",
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(40.dp),
        )
    }
}
