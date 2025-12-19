package com.pnow.data.character.mapper

import com.pnow.data.character.model.CharacterLocationDTO
import com.pnow.domain.model.CharacterLocation
import javax.inject.Inject

class CharacterLocationDTOMapper @Inject constructor() {
    fun map(chLocation: CharacterLocationDTO): CharacterLocation {
        return with(chLocation) {
            CharacterLocation(
                name = name,
                url = url
            )
        }
    }
}
