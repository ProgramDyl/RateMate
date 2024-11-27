package com.example.ratemate.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.ratemate.AppNavHost
import com.example.ratemate.data.database.AppDatabase
import com.example.ratemate.data.model.User
import com.example.ratemate.data.repository.UserRepository
import com.example.ratemate.ui.theme.RateMateTheme
import com.example.ratemate.ui.theme.UserViewModel
import com.example.ratemate.ui.theme.UserViewModelFactory
import com.example.ratemate.home.HomeScreen
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = AppDatabase.getDatabase(this)
        val userRepository = UserRepository(database.userDao())
        val userViewModelFactory = UserViewModelFactory(userRepository)
        userViewModel = ViewModelProvider(this, userViewModelFactory).get(UserViewModel::class.java)

        setContent {
            RateMateTheme {
                AppNavHost(userViewModel = userViewModel)
            }
        }

        // Test inserting a user
        lifecycleScope.launch {
            val user = User(firstName = "John", lastName = "Doe", email = "john.doe@example.com")
            userViewModel.insertUser(user)
        }

        // Test retrieving a user by email
        lifecycleScope.launch {
            userViewModel.getUserByEmail("john.doe@example.com")
        }

        // Observe the user LiveData
        userViewModel.user.observe(this, { user ->
            user?.let {
                // Handle the retrieved user here
                println("Retrieved User: ${user.firstName} ${user.lastName}, Email: ${user.email}")
            }
        })
    }
}
