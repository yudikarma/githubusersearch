package com.karma.githubusersearch.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.karma.githubusersearch.data.local.UserDao
import com.karma.githubusersearch.data.remote.GitHubApiService
import com.karma.githubusersearch.data.remote.GithubClient
import com.karma.githubusersearch.presentation.domain.model.User
import com.karma.githubusersearch.presentation.domain.model.UserDetail
import com.karma.githubusersearch.presentation.domain.repository.GithubRepository
import com.karma.githubusersearch.utils.DataMapper
import com.karma.githubusersearch.utils.ResultState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GithubRepositoryImpl @Inject constructor(
    private val networkService: GitHubApiService,
    private val dao: UserDao
) : GithubRepository {

    /*override suspend fun getUserFromApiPaging(query: String?) =
        Pager(
            config = PagingConfig(
                pageSize = 10,
                maxSize = 30,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { RepoPagingSource(networkService, query) }
        ).liveData*/

    override suspend fun getUserFromApi(username: String): Flow<ResultState<List<User>>> {
        return flow {
            try {
                val response = networkService.getSearchUser(username)
                val userItems = response.items
                val dataMaped = userItems?.let { listSearchUser ->
                    DataMapper.mapUserSearchResponseToDomain(listSearchUser)
                }
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getUserFromApiPaging(username: String): Flow<ResultState<PagingData<User>>> {
        return flow {
            try {
                Pager(
                    config = PagingConfig(
                        pageSize = 10,
                        maxSize = 30,
                        enablePlaceholders = false
                    ),
                    pagingSourceFactory = { RepoPagingSource(networkService, username) }
                ).liveData
            }catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }

    override suspend fun getDetailUserFromApi(username: String): Flow<ResultState<UserDetail>> {
        return flow {
            try {
                val response = networkService.getDetailUser(username)
                val dataMaped = DataMapper.mapUserDetailResponseToDomain(response)
                emit(ResultState.Success(dataMaped))
            } catch (e: Exception) {
                emit(ResultState.Error(e.toString(), 500))
            }
        }.flowOn(Dispatchers.IO)
    }

    override fun fetchAllUser(): Flow<List<User>> {
        return dao.fetchAllUsers().map {
            DataMapper.mapUserEntitiesToDomain(it)
        }
    }

    override fun getUserByUsername(username: String): Flow<List<User>> {
        return dao.getByUsername(username).map {
            DataMapper.mapUserEntitiesToDomain(it)
        }
    }

    override suspend fun addUserToDB(userEntity: User) {
        val data = DataMapper.mapUserDomainToEntity(userEntity)
        return dao.addUserToDB(data)
    }

    override suspend fun deleteUserFromDB(userEntity: User) {
        val data = DataMapper.mapUserDomainToEntity(userEntity)
        return dao.deleteUserFromDB(data)
    }
}