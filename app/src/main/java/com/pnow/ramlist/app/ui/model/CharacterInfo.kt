package com.pnow.ramlist.app.ui.model

import android.os.Parcelable
import com.pnow.domain.model.CharacterStatus
import kotlinx.parcelize.Parcelize

@Parcelize
data class CharacterInfo(
    val id: Int,
    val name: String,
    val species: String,
    val gender: String,
    val imageUrl: String,
    val statusDescription: String,
    val status: CharacterStatus = CharacterStatus.UNKNOWN,
) : Parcelable
