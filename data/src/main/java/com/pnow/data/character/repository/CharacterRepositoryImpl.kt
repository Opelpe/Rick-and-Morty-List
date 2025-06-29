package com.pnow.data.character.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.pnow.data.character.CharactersPagingDataSource
import com.pnow.domain.model.Character
import com.pnow.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val pagingDataSource: CharactersPagingDataSource
) : CharacterRepository {

    override fun getCharacters(): Flow<PagingData<Character>> {
        return Pager(
            config =
            PagingConfig(
                pageSize = 20
            ),
            pagingSourceFactory = { pagingDataSource }
        ).flow
    }
}
