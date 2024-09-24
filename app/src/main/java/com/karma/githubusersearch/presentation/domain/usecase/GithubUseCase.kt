package com.karma.githubusersearch.presentation.domain.usecase

import com.karma.githubusersearch.presentation.domain.model.User
import com.karma.githubusersearch.presentation.domain.model.UserDetail
import com.karma.githubusersearch.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface GithubUseCase {
    suspend fun getUserFromApi(username : String) : Flow<ResultState<List<User>>>
    suspend fun getUserDetailFromApi(username : String) : Flow<ResultState<UserDetail>>

    fun fetchAllUser() : Flow<List<User>>
    suspend fun deleteUserFromDb(user: User)
    suspend fun addUserToDB(usere: User)
    fun getUserByUsername(username: String) : Flow<List<User>>
}