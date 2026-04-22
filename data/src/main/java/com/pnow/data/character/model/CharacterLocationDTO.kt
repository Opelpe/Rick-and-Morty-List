package com.pnow.data.character.model

import com.google.gson.annotations.SerializedName
import com.pnow.domain.model.CharacterLocation

data class CharacterLocationDTO(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String,
) {
    fun toDomain(): CharacterLocation = with(this) {
        CharacterLocation(
            name = name,
            url = url,
        )
    }
}
