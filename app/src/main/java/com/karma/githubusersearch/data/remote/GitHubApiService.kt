package com.karma.githubusersearch.data.remote

import com.karma.githubusersearch.data.remote.response.SearchResponse
import com.karma.githubusersearch.data.remote.response.UserDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApiService {
    @GET("search/users?")
    suspend fun getSearchUser(
        @Query("q") q : String
    ) : SearchResponse

    @GET("search/repositories")
    suspend fun getSearchUserPaging(
        @Query("q") query: String?, @Query("page") page: Int, @Query("per_page") perPage: Int
    ): SearchResponse

    @GET("users/{username}")
    suspend fun getDetailUser(
        @Path("username") username: String
    ) : UserDetailResponse
}