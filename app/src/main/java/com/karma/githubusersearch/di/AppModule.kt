package com.karma.githubusersearch.di

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import androidx.room.Room
import com.karma.githubusersearch.presentation.domain.usecase.GithubUseCase
import com.karma.githubusersearch.presentation.domain.usecase.GithubUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    abstract fun provideUserUseCase(userUseCaseImpl: GithubUseCaseImpl) : GithubUseCase



}