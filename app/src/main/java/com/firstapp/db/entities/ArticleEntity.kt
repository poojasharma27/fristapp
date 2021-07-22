package com.firstapp.db.entities


import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class ArticleEntity(
  var author: String,
  var content: String,
  var description: String,
  var publishedAt: String,
  var title: String,
  var url: String,
  var urlToImage: String?
){
  @PrimaryKey(autoGenerate = true)
  var newsId = 0
}