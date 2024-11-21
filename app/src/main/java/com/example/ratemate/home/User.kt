package com.example.ratemate.home

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String,

    //TODO: migrate this field into the user class
//    val profilePicture: String
)
