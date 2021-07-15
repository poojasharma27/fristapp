package com.firstapp.util

import com.firstapp.db.entities.ArticleEntity
import com.firstapp.network.model.Article


fun Article.toArticleEntity() : ArticleEntity{
    val articleEntity = ArticleEntity(
        this.author?:"author not found",
        this.content,
        this.description,
        this.publishedAt,
        this.title,
        this.url,
        this.urlToImage
    )
    return articleEntity
}