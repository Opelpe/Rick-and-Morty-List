package com.pnow.ramlist.app.ui.state

data class DetailsUiState(
    val character: CharacterInfoState = CharacterInfoState.Loading,
    val episodes: EpisodeState = EpisodeState.Loading,
)
