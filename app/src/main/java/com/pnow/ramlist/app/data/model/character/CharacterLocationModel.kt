package com.pnow.ramlist.app.data.model.character

import com.google.gson.annotations.SerializedName

data class CharacterLocationModel(
    @field:SerializedName("name")
    val name: String,
    @field:SerializedName("url")
    val url: String
)