package com.pnow.data.character.mapper

import com.pnow.data.character.model.CharacterDTO
import com.pnow.domain.model.Character
import javax.inject.Inject

class CharacterDTOMapper @Inject constructor(
    private val chLocationMapper: CharacterLocationDTOMapper
) {

    fun map(dto: CharacterDTO): Character {
        return with(dto) {
            Character(
                id = id,
                name = name,
                status = status,
                species = species,
                gender = gender,
                imageUrl = imageUrl,
                origin = chLocationMapper.map(origin),
                location = chLocationMapper.map(location),
                episodeUrl = episodeUrl
            )
        }
    }
}
