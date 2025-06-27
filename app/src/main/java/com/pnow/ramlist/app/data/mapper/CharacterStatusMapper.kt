package com.pnow.ramlist.app.data.mapper

import com.pnow.ramlist.app.ui.adapter.CharacterStatus
import javax.inject.Inject

class CharacterStatusMapper @Inject constructor() {
    fun mapToStatusDrawable(status: String): CharacterStatus {
        return when (status) {
            "Alive" -> CharacterStatus.Alive
            "Dead" -> CharacterStatus.Dead
            "unknown" -> CharacterStatus.Unknown
            else -> CharacterStatus.Unknown
        }
    }

    fun mapToStatusText(status: String): String {
        return when (status) {
            "unknown" -> "Unknown"
            else -> status
        }
    }
}