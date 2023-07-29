package com.aria.roomdatabasedemoapplication.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class User(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("id_user")
    var id: Int,
    @ColumnInfo("user_name")
    var userName: String,
    @ColumnInfo("email")
    var email: String
)