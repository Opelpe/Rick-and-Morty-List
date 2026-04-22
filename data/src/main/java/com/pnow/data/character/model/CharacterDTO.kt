package com.pnow.data.character.model

import com.google.gson.annotations.SerializedName
import com.pnow.domain.model.Character

data class CharacterDTO(
    @field:SerializedName("id")
    val id: Int,
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("status")
    val status: String,
    @field:SerializedName("species")
    val species: String,
    @field:SerializedName("gender")
    val gender: String,
    @field:SerializedName("image")
    val imageUrl: String,
    @field:SerializedName("origin")
    val origin: CharacterLocationDTO,
    @field:SerializedName("location")
    val location: CharacterLocationDTO,
    @field:SerializedName("episode")
    val episodeUrl: List<String>,
) {
    fun toDomain(): Character {
        return with(this) {
            Character(
                id = id,
                name = name,
                status = status,
                species = species,
                gender = gender,
                imageUrl = imageUrl,
                origin = origin.toDomain(),
                location = location.toDomain(),
                episodeUrl = episodeUrl,
            )
        }
    }
}
