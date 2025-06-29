package com.pnow.data.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pnow.data.character.mapper.CharacterDTOMapper
import com.pnow.domain.model.Character
import javax.inject.Inject

class CharactersPagingDataSource
@Inject
constructor(
    private val api: CharacterApi,
    private val mapper: CharacterDTOMapper
) :
    PagingSource<Int, Character>() {
    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: 1
        return try {
            val response = api.getCharacters(page)
            val data = response.results.map(mapper::map)
            LoadResult.Page(
                data = data,
                prevKey = if (page <= 1) null else page.minus(1),
                nextKey = if (data.isEmpty()) null else page.plus(1)
            )
        } catch (exception: Exception) {
            return LoadResult.Error(exception)
        }
    }
}
