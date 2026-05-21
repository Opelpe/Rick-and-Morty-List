package com.pnow.ramlist.app.data.mapper

import androidx.paging.PagingData
import androidx.paging.map
import com.pnow.domain.model.Character
import com.pnow.domain.model.CharacterStatus
import com.pnow.ramlist.app.ui.model.CharacterInfo
import javax.inject.Inject

class CharacterMapper
@Inject
constructor() {

    fun map(pagingData: PagingData<Character>): PagingData<CharacterInfo> = pagingData.map { character ->
        val status = CharacterStatus.fromString(character.status)
        CharacterInfo(
            id = character.id,
            name = character.name,
            statusDescription = status.statusTitle,
            imageUrl = character.imageUrl,
            status = status,
            species = character.species,
            gender = character.gender,
        )
    }
}
