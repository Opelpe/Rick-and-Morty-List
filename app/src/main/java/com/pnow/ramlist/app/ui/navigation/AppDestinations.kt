package com.pnow.ramlist.app.ui.navigation

object AppDestinations {
    const val CHARACTER_ID_KEY = "character_id"
    const val CHARACTER_LIST = "character_list"
    const val CHARACTER_DETAILS = "character_details"
    const val CHARACTER_DETAILS_ROUTE = "$CHARACTER_DETAILS/{$CHARACTER_ID_KEY}"

    fun characterDetails(id: Int) = "$CHARACTER_DETAILS/$id"
}
