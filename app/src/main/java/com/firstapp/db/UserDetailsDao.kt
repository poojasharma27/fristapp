package com.firstapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.firstapp.db.entities.ArticleEntity
import com.firstapp.db.entities.UserEntity
import com.firstapp.network.model.Article

@Dao
interface UserDetailsDao {

    @Insert
    suspend fun addDetails(userEntity: UserEntity)

    @Query(" SELECT * FROM UserEntity ORDER BY uid DESC")
    suspend fun getUserDetails() : List<UserEntity>

    @Insert
     suspend fun addMultipleDetails(vararg userEntity: UserEntity)

     @Insert
     suspend fun addArticle(articleEntity:List<ArticleEntity>)

     @Query("SELECT * FROM ArticleEntity ORDER BY newsId DESC ")
     suspend fun  getArticleEntity(): List<ArticleEntity>

     @Delete
     suspend fun deleteArticle(articleEntity: ArticleEntity)
}