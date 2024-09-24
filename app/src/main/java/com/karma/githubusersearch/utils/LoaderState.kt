package com.karma.githubusersearch.utils

sealed class LoaderState {
    object ShowLoading: LoaderState()
    object HideLoading: LoaderState()
}