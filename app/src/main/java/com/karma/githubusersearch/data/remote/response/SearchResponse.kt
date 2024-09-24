package com.karma.githubusersearch.data.remote.response

import androidx.annotation.Keep

@Keep
data class SearchResponse(
    val incomplete_results: Boolean,
    val items: List<UserResponse>,
    val total_count: Int
)