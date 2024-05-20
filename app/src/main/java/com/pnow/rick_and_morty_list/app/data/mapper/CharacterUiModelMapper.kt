package com.pnow.rick_and_morty_list.app.data.mapper

import com.pnow.rick_and_morty_list.app.data.networking.model.CharacterResponseModel
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import javax.inject.Inject

class CharacterUiModelMapper @Inject constructor(private val characterStatusMapper: CharacterStatusMapper) {

    fun mapToUIModel(response: CharacterResponseModel): List<CharacterUIModel> {

        val newList = mutableListOf<CharacterUIModel>()
        response.results.forEach {

            val uiModel = CharacterUIModel(
                it.id,
                it.name,
                characterStatusMapper.mapToStatusText(it.status),
                characterStatusMapper.mapToStatusDrawable(it.status),
                it.species,
                it.gender,
                it.imageUrl,
                it.origin,
                it.location,
                it.episodeUrl
            )
            if (!newList.contains(uiModel)) {
                newList.add(uiModel)
            }
        }
        return newList
    }
}