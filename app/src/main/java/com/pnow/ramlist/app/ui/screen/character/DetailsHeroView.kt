package com.pnow.ramlist.app.ui.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pnow.domain.model.CharacterStatus
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.DetailsInfo
import com.pnow.ramlist.app.ui.screen.details.CharacterDetailsColors
import com.pnow.ramlist.app.ui.screen.previewDetails
import com.pnow.ramlist.app.ui.screen.status.StatusPill
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun DetailsHeroView(detailsInfo: DetailsInfo, modifier: Modifier = Modifier) {
    Box(
        modifier =
        modifier
            .fillMaxWidth()
            .background(CharacterDetailsColors.HeroBackground)
            .padding(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val characterInfo = detailsInfo.character

            HeroDetailsIcon(
                imageUrl = characterInfo.imageUrl,
                name = characterInfo.name,
            )

            HeroDetailsInfo(
                status = characterInfo.status,
                label = characterInfo.statusDescription,
                name = characterInfo.name,
                species = characterInfo.species,
                gender = characterInfo.gender,
                modifier =
                Modifier
                    .weight(1f)
                    .padding(top = 4.dp),
            )
        }
    }
}

@Composable
private fun HeroDetailsInfo(
    status: CharacterStatus,
    label: String,
    name: String,
    species: String,
    gender: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(16.dp),
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.TextPrimary,
            lineHeight = 24.sp,
        )

        StatusPill(
            status = status,
            label = label,
            background = CharacterDetailsColors.StatusPillBackground,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(32.dp)) {
            MetaItem(label = stringResource(R.string.common_word_species), value = species)
            MetaItem(label = stringResource(R.string.common_word_gender), value = gender)
        }
    }
}

@Composable
private fun MetaItem(label: String, value: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(top = 15.dp),
    ) {
        Text(
            text = label.uppercase(),
            fontSize = 10.sp,
            color = CharacterDetailsColors.TextMuted,
            letterSpacing = 0.5.sp,
            lineHeight = 10.sp,
        )
        Text(
            text = value,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.TextPrimary,
        )
    }
}

@Composable
@Preview
private fun DetailsCharacterPreview() {
    RickAndMortyTheme {
        DetailsHeroView(
            detailsInfo = previewDetails,
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
        )
    }
}
