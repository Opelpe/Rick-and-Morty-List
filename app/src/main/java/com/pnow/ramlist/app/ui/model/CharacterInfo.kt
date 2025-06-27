package com.pnow.ramlist.app.ui.model

import com.pnow.ramlist.app.data.model.character.CharacterLocationModel
import com.pnow.ramlist.app.ui.adapter.CharacterStatus
import java.io.Serializable

sealed class CharacterInfo {

    data class Details(
        val originModel: LocationUIModel,
        val locationModel: LocationUIModel,
    ) : CharacterInfo()

    data class ListItem(
        val id: Int,
        val name: String,
        val status: String,
        val statusDrawable: CharacterStatus,
        val species: String,
        val gender: String,
        val imageUrl: String,
        val origin: CharacterLocationModel,
        val location: CharacterLocationModel,
        val episodeUrl: List<String>
    ) : CharacterInfo(), Serializable
}
