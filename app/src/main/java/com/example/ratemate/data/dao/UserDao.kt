package com.example.ratemate.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ratemate.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?
}
