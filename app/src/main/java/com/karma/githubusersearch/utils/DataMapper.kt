package com.karma.githubusersearch.utils

import com.karma.githubusersearch.data.local.UserEntitiy
import com.karma.githubusersearch.data.remote.response.SearchResponse
import com.karma.githubusersearch.data.remote.response.UserDetailResponse
import com.karma.githubusersearch.data.remote.response.UserResponse
import com.karma.githubusersearch.presentation.domain.model.User
import com.karma.githubusersearch.presentation.domain.model.UserDetail

object DataMapper {

    fun mapUserSearchResponseToDomain(data: List<UserResponse>): List<User> =
        data.map {
            User(
                username = it.username.toString(),
                name = it.name,
                avatarUrl = it.avatarUrl,
                followersUrl = it.followersUrl,
                bio = it.bio,
                company = it.company,
                publicRepos = it.publicRepos,
                followingUrl = it.followingUrl,
                followers = it.followers,
                following = it.following,
                location = it.location
            )
        }


    fun mapUserDetailResponseToDomain(data: UserDetailResponse): UserDetail =
        UserDetail(
            username = data.login.toString(),
            name = data.name,
            avatarUrl = data.avatarUrl,
            followersUrl = data.followersUrl,
            bio = data.bio,
            company = data.company,
            publicRepos = data.publicRepos,
            followingUrl = data.followingUrl,
            followers = data.followers,
            following = data.following,
            location = data.location
        )


    fun mapUserEntitiesToDomain(data: List<UserEntitiy>): List<User> =
        data.map {
            User(
                username = it.username,
                name = it.name,
                avatarUrl = it.avatarUrl,
                followersUrl = it.followersUrl,
                bio = it.bio,
                company = it.company,
                publicRepos = it.publicRepos,
                followingUrl = it.followingUrl,
                followers = it.followers,
                following = it.following,
                location = it.location
            )
        }



    fun mapUserDetailToUser(it: User): UserEntitiy =
        UserEntitiy(
            username = it.username,
            name = it.name,
            avatarUrl = it.avatarUrl,
            followersUrl = it.followersUrl,
            bio = it.bio,
            company = it.company,
            publicRepos = it.publicRepos,
            followingUrl = it.followingUrl,
            followers = it.followers,
            following = it.following,
            location = it.location
        )
    fun mapUserDomainToEntity(data: User): UserEntitiy =
        UserEntitiy(
            username = data.username,
            name = data.name,
            avatarUrl = data.avatarUrl,
            followersUrl = data.followersUrl,
            bio = data.bio,
            company = data.company,
            publicRepos = data.publicRepos,
            followingUrl = data.followingUrl,
            followers = data.followers,
            following = data.following,
            location = data.location
        )

}