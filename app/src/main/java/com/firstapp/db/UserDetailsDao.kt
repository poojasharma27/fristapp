package com.firstapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.firstapp.db.entities.ArticleEntity
import com.firstapp.db.entities.ImagesEntity
import com.firstapp.db.entities.UserEntity

@Dao
interface UserDetailsDao {

    @Insert
    suspend fun addDetails(userEntity: UserEntity)
   /* ORDER BY uid DESC*/
    @Query(" SELECT * FROM UserEntity ORDER BY uid DESC")
    suspend fun getUserDetails() : List<UserEntity>

    @Insert
     suspend fun addMultipleDetails(vararg userEntity: UserEntity)

     @Insert
     suspend fun addArticle(articleEntity:List<ArticleEntity>)

     @Query("SELECT * FROM ArticleEntity ORDER BY newsId ASC ")
     suspend fun  getArticleEntity(): List<ArticleEntity>

     @Delete
     suspend fun deleteArticle(articleEntity: ArticleEntity)

    @Query("DELETE FROM UserEntity WHERE uId = :userId")
     suspend fun deleteByUserId(userId: Long)
    @Insert
    suspend fun addImages(imagesEntity: ImagesEntity)

    @Query("SELECT * FROM ImagesEntity ORDER BY imageId ASC ")
    suspend fun  getImagesEntity(): List<ImagesEntity>

}