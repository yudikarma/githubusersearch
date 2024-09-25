package com.karma.githubusersearch.data.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.karma.githubusersearch.data.remote.GitHubApiService
import com.karma.githubusersearch.data.remote.response.UserResponse
import retrofit2.HttpException
import java.io.IOException

private const val START_PAGE_INDEX = 1

class RepoPagingSource(
    private val searchApi: GitHubApiService,
    private val query: String?
) : PagingSource<Int, UserResponse>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserResponse> {
        val position = params.key ?: START_PAGE_INDEX
        return try {
            val apiResponse =
                searchApi.getSearchUserPaging(query, position, params.loadSize)
            val repos = apiResponse.items

            LoadResult.Page(
                data = repos,
                prevKey = if (position == START_PAGE_INDEX) null else position - 1,
                nextKey = if (repos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserResponse>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}