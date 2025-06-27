package com.pnow.ramlist.app.data.model.character

import com.google.gson.annotations.SerializedName

data class CharacterModel(
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
    val origin: CharacterLocationModel,
    @field:SerializedName("location")
    val location: CharacterLocationModel,
    @field:SerializedName("episode")
    val episodeUrl: List<String>,
)
