package com.karma.githubusersearch.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [UserEntitiy::class],
    version = 1,
    exportSchema = false
)
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

}