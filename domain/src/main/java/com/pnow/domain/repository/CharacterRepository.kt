package com.pnow.domain.repository

import androidx.paging.PagingData
import com.pnow.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    fun getCharacters(): Flow<PagingData<Character>>
}
