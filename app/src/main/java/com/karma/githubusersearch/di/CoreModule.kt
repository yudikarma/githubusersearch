package com.karma.githubusersearch.di

import android.app.Application
import android.database.sqlite.SQLiteDatabase
import android.net.Network
import androidx.room.Room
import com.karma.githubusersearch.data.local.UserDao
import com.karma.githubusersearch.data.local.UserDatabase
import com.karma.githubusersearch.data.remote.GitHubApiService
import com.karma.githubusersearch.data.remote.GithubClient
import com.karma.githubusersearch.utils.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideNetworkService(): GitHubApiService {
        return GithubClient.retrofitClient().create(GitHubApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideAppDatabase(app: Application): UserDatabase {

        return Room.databaseBuilder(
            app,
            UserDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideUserDao(appDatabase: UserDatabase): UserDao {
        return appDatabase.userDao()
    }

}