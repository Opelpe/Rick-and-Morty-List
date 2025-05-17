package com.pnow.rick_and_morty_list.app.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.pnow.rick_and_morty_list.app.data.model.character.CharacterModel
import com.pnow.rick_and_morty_list.app.ui.model.CharacterUIModel
import javax.inject.Inject

class CharacterUiModelMapper @Inject constructor(private val characterStatusMapper: CharacterStatusMapper) {

    fun map(pagingData: PagingData<CharacterModel>): PagingData<CharacterUIModel> {
        return pagingData.map { character ->
            CharacterUIModel(
                id = character.id,
                name = character.name,
                status = characterStatusMapper.mapToStatusText(character.status),
                statusDrawable = characterStatusMapper.mapToStatusDrawable(character.status),
                species = character.species,
                gender = character.gender,
                imageUrl = character.imageUrl,
                origin = character.origin,
                location = character.location,
                episodeUrl = character.episodeUrl
            )
        }
    }
}