package com.pnow.domain.model

data class Character(
    val id: Int,
    val name: String,
    val status: String,
    val species: String,
    val gender: String,
    val imageUrl: String,
    val origin: CharacterLocation,
    val location: CharacterLocation,
    val episodeUrl: List<String>
)
