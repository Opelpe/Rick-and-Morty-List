package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun ErrorMessage(message: String, modifier: Modifier = Modifier) {
    Text(
        text = message,
        color = CharacterDetailsColors.Error,
        fontSize = 13.sp,
        modifier = modifier.padding(horizontal = 20.dp, vertical = 8.dp),
    )
}

@Composable
@Preview
private fun ErrorMessagePreview() {
    RickAndMortyTheme {
        ErrorMessage(
            message = "Failed to load data",
        )
    }
}
