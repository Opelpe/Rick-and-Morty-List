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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
            .padding(horizontal = 10.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(5.dp),
    ) {
        Box(
            modifier =
            Modifier
                .size(7.dp)
                .background(status.dotColor, CircleShape),
        )
        Text(
            text = label.uppercase(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium,
            color = CharacterDetailsColors.StatusPillText,
            letterSpacing = 0.5.sp,
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
                .padding(150.dp),
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
                .padding(150.dp),
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
                .padding(150.dp),
            background = CharacterDetailsColors.StatusPillBackground,
            status = CharacterStatus.UNKNOWN,
            label = "Unknown",
        )
    }
}
