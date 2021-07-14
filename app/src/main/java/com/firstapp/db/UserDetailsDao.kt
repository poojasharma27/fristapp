package com.firstapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.firstapp.db.entities.UserEntity

@Dao
interface UserDetailsDao {

    @Insert
    suspend fun addDetails(userEntity: UserEntity)

    @Query(" SELECT * FROM UserEntity ORDER BY uid DESC")
    suspend fun getUserDetails() : List<UserEntity>

    @Insert
     suspend fun addMultipleDetails(vararg userEntity: UserEntity)

}