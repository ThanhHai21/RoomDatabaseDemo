package com.aria.roomdatabasedemoapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aria.roomdatabasedemoapplication.database.UserDao

class MainViewModelFactory(private var dao: UserDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknow View Model Class")
    }
}