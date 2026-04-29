package com.pnow.ramlist.app.ui.screen.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.pnow.ramlist.R
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun CharacterListHeader(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier =
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
                .padding(start = Dimens.LogoWidthSize)
                .align(Alignment.Center),
            text = stringResource(R.string.module_list_logo_motto).uppercase(),
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold,
        )

        Image(
            painter = painterResource(id = R.drawable.ic_rick_morty_logo),
            modifier =
            Modifier
                .size(Dimens.LogoWidthSize, Dimens.LogoHeightSize)
                .align(Alignment.CenterStart),
            contentDescription = null,
        )
    }
}

@Composable
@Preview
private fun CharacterListHeaderPreview() {
    RickAndMortyTheme(darkTheme = false) {
        CharacterListHeader(Modifier.fillMaxWidth())
    }
}
