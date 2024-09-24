package com.karma.githubusersearch.presentation.domain.usecase

import com.karma.githubusersearch.presentation.domain.model.User
import com.karma.githubusersearch.presentation.domain.model.UserDetail
import com.karma.githubusersearch.presentation.domain.repository.GithubRepository
import com.karma.githubusersearch.utils.ResultState
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GithubUseCaseImpl @Inject constructor(private val githubRepository: GithubRepository) :GithubUseCase {
    override suspend fun getUserFromApi(username: String): Flow<ResultState<List<User>>> = githubRepository.getUserFromApi(username)

    override suspend fun getUserDetailFromApi(username: String): Flow<ResultState<UserDetail>> = githubRepository.getDetailUserFromApi(username)

    override fun fetchAllUser(): Flow<List<User>> = githubRepository.fetchAllUser()

    override suspend fun deleteUserFromDb(user: User) = githubRepository.deleteUserFromDB(user)

    override suspend fun addUserToDB(user: User) = githubRepository.addUserToDB(user)

    override fun getUserByUsername(username: String): Flow<List<User>> = githubRepository.getUserByUsername(username)
}