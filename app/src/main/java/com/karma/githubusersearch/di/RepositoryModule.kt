package com.karma.githubusersearch.di

import com.karma.githubusersearch.data.repository.GithubRepositoryImpl
import com.karma.githubusersearch.presentation.domain.repository.GithubRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun provideUserRepository(githubRepositoryImpl: GithubRepositoryImpl): GithubRepository

}