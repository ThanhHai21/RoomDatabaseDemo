package com.aria.roomdatabasedemoapplication.viewmodel;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aria.roomdatabasedemoapplication.data.UserDao
import com.aria.roomdatabasedemoapplication.model.User
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
