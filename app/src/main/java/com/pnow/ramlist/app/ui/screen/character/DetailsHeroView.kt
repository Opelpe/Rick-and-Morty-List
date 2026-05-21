package com.pnow.ramlist.app.ui.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.pnow.domain.model.CharacterStatus
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.model.DetailsInfo
import com.pnow.ramlist.app.ui.screen.previewDetails
import com.pnow.ramlist.app.ui.screen.status.StatusPill
import com.pnow.ramlist.core.ui.dimens.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme
import com.pnow.ramlist.core.ui.theme.detailsColors

@Composable
fun DetailsHeroView(modifier: Modifier = Modifier, detailsInfo: DetailsInfo) {
    Row(
        modifier =
        modifier
            .fillMaxWidth()
            .background(MaterialTheme.detailsColors.heroBackground)
            .padding(Dimens.Spacing8),
        horizontalArrangement = Arrangement.spacedBy(Dimens.Spacing24),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        val characterInfo = detailsInfo.character

        HeroIcon(
            modifier = Modifier.size(Dimens.DetailsIconSize),
            contentScale = ContentScale.Fit,
            shape = RoundedCornerShape(Dimens.CornerRadius6),
            background = MaterialTheme.detailsColors.avatarBackground,
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
                .padding(top = Dimens.Spacing4),
        )
    }
}

@Composable
private fun HeroDetailsInfo(
    modifier: Modifier = Modifier,
    status: CharacterStatus,
    label: String,
    name: String,
    species: String,
    gender: String,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(Dimens.Spacing16),
    ) {
        Text(
            text = name,
            fontSize = Dimens.TextSize20,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.detailsColors.textPrimary,
            lineHeight = Dimens.TextSize24,
        )

        StatusPill(
            status = status,
            label = label,
            background = MaterialTheme.detailsColors.statusPillBackground,
        )

        Row(horizontalArrangement = Arrangement.spacedBy(Dimens.Spacing32)) {
            MetaItem(
                modifier = Modifier.weight(1f),
                label = stringResource(R.string.common_word_species),
                value = species,
            )
            MetaItem(
                modifier = Modifier.weight(1f),
                label = stringResource(R.string.common_word_gender),
                value = gender,
            )
        }
    }
}

@Composable
private fun MetaItem(modifier: Modifier = Modifier, label: String, value: String) {
    Column(
        modifier = modifier.padding(top = Dimens.Spacing15),
    ) {
        Text(
            text = "${label.uppercase()}:",
            fontSize = Dimens.TextSize10,
            color = MaterialTheme.detailsColors.textMuted,
            letterSpacing = Dimens.LetterSpacingTight,
            lineHeight = Dimens.TextSize10,
        )
        Text(
            text = value,
            fontSize = Dimens.TextSize13,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.detailsColors.textPrimary,
        )
    }
}

@Composable
@Preview
private fun DetailsCharacterPreview() {
    RickAndMortyTheme {
        DetailsHeroView(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            detailsInfo = previewDetails,
        )
    }
}
