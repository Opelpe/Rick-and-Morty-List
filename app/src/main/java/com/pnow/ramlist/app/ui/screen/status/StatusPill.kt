package com.pnow.ramlist.app.ui.screen.status

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.pnow.domain.model.CharacterStatus
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

private val CharacterStatus.dotColor: Color
    get() = when (this) {
        CharacterStatus.ALIVE -> CharacterDetailsColors.StatusAlive
        CharacterStatus.DEAD -> CharacterDetailsColors.StatusDead
        CharacterStatus.UNKNOWN -> CharacterDetailsColors.TextMuted
    }

@Composable
fun StatusPill(modifier: Modifier = Modifier, status: CharacterStatus, label: String, background: Color) {
    Row(
        modifier =
        modifier
            .background(background, RoundedCornerShape(Dimens.CornerRadius8))
            .padding(horizontal = Dimens.Spacing10, vertical = Dimens.Spacing4),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.Spacing5),
    ) {
        Box(
            modifier =
            Modifier
                .size(Dimens.StatusDotSize)
                .background(status.dotColor, CircleShape),
        )
        Text(
            text = label.uppercase(),
            fontSize = Dimens.TextSize11,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.StatusPillText,
            letterSpacing = Dimens.LetterSpacingTight,
        )
    }
}

@Composable
@Preview
private fun AliveStatusPreview() {
    RickAndMortyTheme {
        StatusPill(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(Dimens.Spacing150),
            background = CharacterDetailsColors.StatusPillBackground,
            status = CharacterStatus.ALIVE,
            label = "Alive",
        )
    }
}

@Composable
@Preview
private fun DeadStatusPreview() {
    RickAndMortyTheme {
        StatusPill(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(Dimens.Spacing150),
            background = CharacterDetailsColors.StatusPillBackground,
            status = CharacterStatus.DEAD,
            label = "Dead",
        )
    }
}

@Composable
@Preview
private fun UnknownStatusPreview() {
    RickAndMortyTheme {
        StatusPill(
            modifier =
            Modifier
                .fillMaxWidth()
                .padding(Dimens.Spacing150),
            background = CharacterDetailsColors.StatusPillBackground,
            status = CharacterStatus.UNKNOWN,
            label = "Unknown",
        )
    }
}
