package com.karma.githubusersearch.di

import com.karma.githubusersearch.presentation.domain.usecase.GithubUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface DatabaseModuleDependencies {
    fun userUseCase() : GithubUseCase
}