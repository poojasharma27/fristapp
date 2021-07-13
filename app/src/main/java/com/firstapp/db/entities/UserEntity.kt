package com.firstapp.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class UserEntity(val email:String,
                       val password:String) {

    @PrimaryKey(autoGenerate = true)
    var uId = 0
}