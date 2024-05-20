package com.pnow.rick_and_morty_list.app.ui.model

import com.pnow.rick_and_morty_list.app.data.model.character.CharacterLocationModel
import com.pnow.rick_and_morty_list.app.ui.adapter.CharacterStatus
import java.io.Serializable

data class CharacterUIModel(
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
) : Serializable
