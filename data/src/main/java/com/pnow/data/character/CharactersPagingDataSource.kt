package com.pnow.data.character

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.pnow.domain.model.Character
import javax.inject.Inject
import kotlinx.coroutines.delay
import retrofit2.HttpException

class CharactersPagingDataSource
@Inject
constructor(private val api: CharacterApi) : PagingSource<Int, Character>() {

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val closestPage = state.closestPageToPosition(anchorPosition)
        return closestPage?.prevKey?.plus(1) ?: closestPage?.nextKey?.minus(1)
    }

    private suspend fun loadWithRetry(params: LoadParams<Int>, retryCount: Int = 0): LoadResult<Int, Character> {
        val page = params.key ?: FIRST_PAGE
        return try {
            val response = api.getCharacters(page)
            val data = response.results.map { it.toDomain() }
            LoadResult.Page(
                data = data,
                prevKey = if (page <= FIRST_PAGE) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1,
            )
        } catch (exception: HttpException) {
            if (exception.code() == HTTP_TOO_MANY_REQUESTS && retryCount < MAX_RETRY_COUNT) {
                delay(RETRY_DELAY_MS * (retryCount + 1))
                loadWithRetry(params, retryCount + 1)
            } else {
                LoadResult.Error(exception)
            }
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override suspend fun load(params: LoadParams<Int>) = loadWithRetry(params)

    companion object {
        private const val FIRST_PAGE = 1
        private const val MAX_RETRY_COUNT = 3
        private const val RETRY_DELAY_MS = 2000L
        private const val HTTP_TOO_MANY_REQUESTS = 429
    }
}
