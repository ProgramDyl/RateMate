package com.example.ratemate.ui.theme

import androidx.lifecycle.*
import com.example.ratemate.data.model.User
import com.example.ratemate.data.repository.UserRepository
import kotlinx.coroutines.launch


class UserViewModel(private val userRepository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User> get() = _user as LiveData<User>

    fun insertUser(user: User) {
        viewModelScope.launch {
            userRepository.insertUser(user)
        }
    }

    fun getUserById(id: Int) {
        viewModelScope.launch {
            val retrievedUser = userRepository.getUserById(id)
            _user.postValue(retrievedUser)
        }
    }

    fun getUserByEmail(email: String) {
        viewModelScope.launch {
            val retrievedUser = userRepository.getUserByEmail(email)
            _user.postValue(retrievedUser)
        }
    }
}
