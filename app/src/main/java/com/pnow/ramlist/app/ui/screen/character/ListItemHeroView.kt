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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pnow.ramlist.app.ui.model.CharacterInfo
import com.pnow.ramlist.app.ui.screen.previewCharacter
import com.pnow.ramlist.app.ui.screen.status.StatusPill
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun ListItemHeroView(modifier: Modifier = Modifier, characterInfo: CharacterInfo) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(24.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        HeroIcon(
            modifier = Modifier.size(Dimens.ItemIconWidth, Dimens.ItemIconHeight),
            shape = RoundedCornerShape(Dimens.CornerRadius8),
            contentScale = ContentScale.FillBounds,
            imageUrl = characterInfo.imageUrl,
            name = characterInfo.name,
        )

        Column(
            modifier = Modifier.weight(1f).padding(top = 4.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Text(
                text = characterInfo.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.colorScheme.onSurface,
                lineHeight = 28.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            StatusPill(
                modifier = Modifier.padding(start = 2.dp),
                status = characterInfo.status,
                label = characterInfo.statusDescription,
                background = MaterialTheme.colorScheme.surfaceVariant,
            )
        }
    }
}

@Composable
@Preview
private fun ListCharacterPreview() {
    RickAndMortyTheme {
        ListItemHeroView(
            modifier = Modifier.background(MaterialTheme.colorScheme.background),
            characterInfo = previewCharacter,
        )
    }
}
