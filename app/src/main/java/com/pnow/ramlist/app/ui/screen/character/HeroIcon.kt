package com.pnow.ramlist.app.ui.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.screen.previewCharacter
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.Dimens

@Composable
fun HeroIcon(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.FillBounds,
    shape: Shape = RoundedCornerShape(Dimens.CornerRadius8),
    background: Color = MaterialTheme.colorScheme.surfaceVariant,
    imageUrl: String,
    name: String,
) {
    AsyncImage(
        modifier = modifier
            .clip(shape)
            .background(background),
        model = imageUrl,
        contentDescription =
        stringResource(R.string.character_avatar_description, name),
        contentScale = contentScale,
        placeholder =
        painterResource(R.drawable.ic_rick_morty_transparent_logo),
        error =
        painterResource(R.drawable.ic_rick_morty_transparent_logo),
    )
}

@Preview
@Composable
private fun HeroListItemIconPreview() {
    HeroIcon(
        modifier = Modifier.size(width = Dimens.ItemIconWidth, height = Dimens.ItemIconHeight),
        contentScale = ContentScale.FillBounds,
        shape = RoundedCornerShape(Dimens.CornerRadius8),
        imageUrl = previewCharacter.imageUrl,
        name = previewCharacter.name,
    )
}

@Preview
@Composable
private fun HeroDetailsIconPreview() {
    HeroIcon(
        modifier = Modifier.size(Dimens.DetailsIconSize),
        contentScale = ContentScale.Fit,
        shape = RoundedCornerShape(Dimens.CornerRadius6),
        background = CharacterDetailsColors.AvatarBackground,
        imageUrl = previewCharacter.imageUrl,
        name = previewCharacter.name,
    )
}
