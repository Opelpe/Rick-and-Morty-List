package com.pnow.ramlist.app.ui.screen.details

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pnow.ramlist.R
import com.pnow.ramlist.core.ui.theme.CharacterDetailsColors
import com.pnow.ramlist.core.ui.theme.Dimens
import com.pnow.ramlist.core.ui.theme.RickAndMortyTheme

@Composable
fun RetryButton(modifier: Modifier = Modifier, onClick: () -> Unit) {
    IconButton(
        modifier = modifier,
        onClick = onClick,
        colors = IconButtonDefaults.iconButtonColors(
            contentColor = CharacterDetailsColors.TextMuted,
        ),
    ) {
        Icon(
            imageVector = Icons.Default.Refresh,
            contentDescription = null,
            modifier = Modifier.padding(Dimens.Spacing8),
        )
    }
}

@Composable
fun RetryButtonWithMessage(modifier: Modifier = Modifier, onRetry: () -> Unit, message: String?) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(Dimens.Spacing10),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        RetryButton(
            onClick = onRetry,
            modifier = Modifier.size(Dimens.RetryButtonSize),
        )

        val text = message ?: stringResource(R.string.common_word_retry)
        Text(
            modifier = Modifier.padding(horizontal = Dimens.Spacing20, vertical = Dimens.Spacing4),
            text = text,
            color = CharacterDetailsColors.Error,
            fontSize = Dimens.TextSize13,
        )
    }
}

@Composable
@Preview
private fun RetryButtonWithMessagePreview() {
    RickAndMortyTheme {
        RetryButtonWithMessage(
            onRetry = {},
            message = "Failed to load data, retry!",
        )
    }
}

@Composable
@Preview(showBackground = true, backgroundColor = 0xFFF9F0EE)
private fun RetryButtonPreview() {
    RickAndMortyTheme {
        RetryButton(
            modifier = Modifier
                .size(Dimens.ProgressSizeSmall),
            onClick = {},
        )
    }
}
