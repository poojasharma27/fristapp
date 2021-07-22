package com.firstapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.firstapp.db.entities.ArticleEntity
import com.firstapp.db.entities.ImagesEntity
import com.firstapp.db.entities.UserEntity

@Database(entities = arrayOf(UserEntity::class,ArticleEntity::class,ImagesEntity::class),version = 1)
abstract class AppDataBase : RoomDatabase() {

    abstract fun userDetailsDao() : UserDetailsDao


    companion object {

        @Volatile private var instance: AppDataBase? = null
        private val LOCK = Any()

         operator fun invoke(context: Context)= instance ?: synchronized(LOCK){
              instance ?: buildDatabase(context).also {
                  instance = it
              }
          }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            AppDataBase::class.java, "todo-list.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}


