package com.pnow.data.character.model

import com.google.gson.annotations.SerializedName

data class CharacterLocationDTO(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String
)
