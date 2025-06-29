package com.pnow.ramlist.app.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.pnow.domain.model.Character
import com.pnow.ramlist.app.ui.model.CharacterInfo
import javax.inject.Inject

class CharacterUiModelMapper
    @Inject
    constructor(private val characterStatusMapper: CharacterStatusMapper) {
        fun map(pagingData: PagingData<Character>): PagingData<CharacterInfo.ListItem> {
            return pagingData.map { character ->
                CharacterInfo.ListItem(
                    id = character.id,
                    name = character.name,
                    status = characterStatusMapper.mapToStatusText(character.status),
                    statusDrawable = characterStatusMapper.mapToStatusDrawable(character.status),
                    species = character.species,
                    gender = character.gender,
                    imageUrl = character.imageUrl,
                    origin = character.origin,
                    location = character.location,
                    episodeUrl = character.episodeUrl,
                )
            }
        }
    }
