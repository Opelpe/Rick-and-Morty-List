package com.pnow.domain.model
//todo czy to ma sens żeby obiekt episdoe iststniał bez name, airDate i episodeNumber? chyba bez sensu go mieć tylko z z id więc te pola nie powinny być nullable
data class Episode(
    val id: Int,
    val name: String?,
    val airDate: String?,
    val episodeNumber: String?,
)
