package com.karma.githubusersearch.data.remote.response

import androidx.annotation.Keep
import androidx.room.ColumnInfo

@Keep
data class UserResponse(
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