package com.karma.githubusersearch.di

import android.content.Context
import com.karma.githubusersearch.presentation.ui.MainActivity
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [DatabaseModuleDependencies::class])
interface DatabaseComponent {
    fun inject(mainActivity: MainActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context) : Builder
        fun appDependencies(databaseModuleDependencies: DatabaseModuleDependencies) : Builder
        fun build() : DatabaseComponent
    }
}