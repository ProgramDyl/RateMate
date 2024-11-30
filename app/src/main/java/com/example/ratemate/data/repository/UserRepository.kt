package com.example.ratemate.data.repository

import com.example.ratemate.data.dao.UserDao
import com.example.ratemate.data.model.User

class UserRepository(private val userDao: UserDao) {

    suspend fun insertUser(user: User) {
        userDao.insertUser(user)
    }

    suspend fun getUserById(id: Int): User {
        return userDao.getUserById(id)
    }

    suspend fun getUserByEmail(email: String): User? {
        return userDao.getUserByEmail(email)
    }

    suspend fun updateUser(user: User) {
        userDao.updateUser(user)
    }
}
