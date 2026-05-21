package com.pnow.ramlist.app.ui.state

import com.pnow.ramlist.app.ui.model.EpisodeInfo

sealed class EpisodeState {
    data object Loading : EpisodeState()

    data class Success(val episodes: List<EpisodeInfo>) : EpisodeState()

    data class Failure(val error: String) : EpisodeState()
}
