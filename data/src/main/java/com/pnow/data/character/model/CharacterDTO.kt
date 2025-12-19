package com.pnow.data.character.model

import com.google.gson.annotations.SerializedName

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
    val episodeUrl: List<String>
)
