package com.karma.githubusersearch.data.local

import android.database.Cursor
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun fetchAllUsers(): Flow<List<UserEntitiy>>

    @Query("SELECT * FROM user_table WHERE username = :userName")
    fun getByUsername(userName: String): Flow<List<UserEntitiy>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUserToDB(userEntity: UserEntitiy)

    @Delete
    suspend fun deleteUserFromDB(userEntity: UserEntitiy)

    /**
     * Cursor for content provider
     */
    @Query("SELECT * FROM user_table")
    fun cursorGetAllUserFavorite(): Cursor
}
