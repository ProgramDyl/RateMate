package com.example.ratemate.data.dao

import androidx.room.*
import com.example.ratemate.data.model.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User)

    @Query("SELECT * FROM user WHERE id = :id")
    suspend fun getUserById(id: Int): User

    @Query("SELECT * FROM user WHERE email = :email")
    suspend fun getUserByEmail(email: String): User?

    @Update
    suspend fun updateUser(user: User)
}
