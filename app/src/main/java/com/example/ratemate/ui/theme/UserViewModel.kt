package com.example.ratemate.ui.theme

import androidx.lifecycle.*
import com.example.ratemate.data.database.User
import com.example.ratemate.data.database.UserRepository
import kotlinx.coroutines.launch

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> get() = _user

    fun loadUser(email: String) {
        viewModelScope.launch {
            _user.value = repository.getUserByEmail(email)
        }
    }

    fun addFavoriteCurrency(currency: String) {
        viewModelScope.launch {
            val currentUser = _user.value
            currentUser?.let {
                val updatedUser = it.copy(favourites = currency)
                repository.updateUser(updatedUser)
                _user.value = updatedUser
            }
        }
    }

    fun setHardcodedFavoriteCurrency() {
        viewModelScope.launch {
            val user = User(
                firstName = "John",
                lastName = "Doe",
                email = "john.doe@example.com",
                favourites = "EUR"
            )
            repository.insertUser(user)
            _user.value = user
        }
    }
}
