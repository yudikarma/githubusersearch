package com.karma.githubusersearch.presentation.domain.repository

import com.karma.githubusersearch.presentation.domain.model.User
import com.karma.githubusersearch.presentation.domain.model.UserDetail
import com.karma.githubusersearch.utils.ResultState
import kotlinx.coroutines.flow.Flow

interface GithubRepository  {

    suspend fun getUserFromApi(username: String) : Flow<ResultState<List<User>>>

    suspend fun getDetailUserFromApi(username: String) : Flow<ResultState<UserDetail>>



    //local
    fun fetchAllUser() : Flow<List<User>>

    fun getUserByUsername(username: String) : Flow<List<User>>

    suspend fun addUserToDB(userEntity: User)

    suspend fun deleteUserFromDB(userEntity: User)



}