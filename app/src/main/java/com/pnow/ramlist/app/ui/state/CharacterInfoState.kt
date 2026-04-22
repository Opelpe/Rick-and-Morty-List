package com.pnow.ramlist.app.ui.state

import com.pnow.ramlist.app.ui.model.DetailsInfo

sealed class CharacterInfoState {
    data object Loading : CharacterInfoState()

    data class Success(val info: DetailsInfo) : CharacterInfoState()

    data class Failure(val error: String) : CharacterInfoState()
}
