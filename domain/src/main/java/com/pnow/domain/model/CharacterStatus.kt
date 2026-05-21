package com.pnow.domain.model

enum class CharacterStatus(val statusTitle: String) {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("Unknown"),
    ;

    companion object {
        fun fromString(value: String): CharacterStatus =
            entries.firstOrNull { it.statusTitle.equals(value, ignoreCase = true) } ?: UNKNOWN
    }
}
