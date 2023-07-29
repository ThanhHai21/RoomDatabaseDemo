package com.aria.roomdatabasedemoapplication;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aria.roomdatabasedemoapplication.database.UserDao
import com.aria.roomdatabasedemoapplication.database.User
import kotlinx.coroutines.launch

class MainViewModel(private var dao: UserDao) : ViewModel(){
    val users = dao.getAllUsers()

    fun insertUser(user: User) = viewModelScope.launch {
        dao.insertUser(user)
    }

    fun updateUser(user: User) = viewModelScope.launch {
        dao.updateUser(user)
    }

    fun deleteUser(user: User) = viewModelScope.launch {
        dao.deleteUser(user)
    }
}
