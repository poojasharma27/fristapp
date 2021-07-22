package com.firstapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ImagesEntity(var images: String?
) {
    @PrimaryKey(autoGenerate = true)
    var imageId = 0
}