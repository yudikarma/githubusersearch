package com.karma.githubusersearch.presentation.domain.model

data class UserDetail (
    val username: String,
    val name: String?,
    val avatarUrl: String?,
    val followingUrl: String?,
    val bio: String?,
    val company: String?,
    val publicRepos: Int?,
    val followersUrl: String?,
    val followers: Int?,
    val following: Int?,
    val location: String?
)