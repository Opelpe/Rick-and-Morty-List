package com.pnow.ramlist.app.ui.screen.character

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import coil.compose.AsyncImage
import com.pnow.ramlist.R
import com.pnow.ramlist.app.ui.screen.details.CharacterDetailsColors
import com.pnow.ramlist.app.ui.screen.previewCharacter
import com.pnow.ramlist.core.ui.theme.Dimens

@Composable
fun HeroDetailsIcon(imageUrl: String, name: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.character_avatar_description, name),
        contentScale = ContentScale.Fit,
        placeholder = painterResource(R.drawable.ic_rick_morty_transparent_logo),
        modifier =
        modifier
            .size(Dimens.DetailsIconSize)
            .clip(RoundedCornerShape(Dimens.CornerRadius6))
            .background(CharacterDetailsColors.AvatarBackground),
        error = painterResource(R.drawable.ic_rick_morty_transparent_logo),
    )
}
//todo jak dla mnie te 2 obrazki są zabardzo podobone do siebie żeby było jako osobne komponenty - parametry powinny wystarczyć

@Composable
fun HeroListItemIcon(imageUrl: String, name: String, modifier: Modifier = Modifier) {
    AsyncImage(
        model = imageUrl,
        contentDescription = stringResource(R.string.character_avatar_description, name),
        contentScale = ContentScale.FillBounds,
        placeholder = painterResource(R.drawable.ic_rick_morty_transparent_logo),
        modifier =
        modifier
            .size(Dimens.ItemIconWidth, Dimens.ItemIconHeight)
            .clip(RoundedCornerShape(Dimens.CornerRadius8))
            .background(MaterialTheme.colorScheme.surfaceVariant),
        error = painterResource(R.drawable.ic_rick_morty_transparent_logo),
    )
}

@Preview
@Composable
private fun HeroListItemIconPreview() {
    HeroListItemIcon(
        imageUrl = "",
        name = previewCharacter.name,
    )
}

@Preview
@Composable
private fun HeroDetailsIconPreview() {
    HeroDetailsIcon(
        imageUrl = "",
        name = previewCharacter.name,
    )
}
