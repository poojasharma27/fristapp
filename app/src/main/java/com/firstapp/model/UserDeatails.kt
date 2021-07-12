package com.firstapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
 data class UserDeatails(val email:String,
val password:String) {

    @PrimaryKey(autoGenerate = true)
    var uId = 0
}