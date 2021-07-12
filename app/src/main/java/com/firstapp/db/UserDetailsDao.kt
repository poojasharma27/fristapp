package com.firstapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.firstapp.model.UserDeatails

@Dao
interface UserDetailsDao {

    @Insert
    suspend fun addDetails(userDeatails: UserDeatails)

    @Query(" SELECT * FROM userDeatails ORDER BY uid DESC")
    suspend fun getuserDeatils() : List<UserDeatails>


    @Insert
     suspend fun addMutlipleDetails(vararg userDeatails: UserDeatails)
}