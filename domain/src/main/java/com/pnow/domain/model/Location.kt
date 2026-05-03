package com.pnow.domain.model

// todo czy ten obiekt z samym id ma sens?
data class Location(
    val id: Int,
    val name: String?,
    val type: String?,
    val dimension: String?,
)
