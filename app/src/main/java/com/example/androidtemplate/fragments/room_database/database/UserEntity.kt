package com.example.androidtemplate.fragments.room_database.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tbl_users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "user_id")
    var userId: Long,
    @ColumnInfo(name = "fullname")
    var fullName: String,
    @ColumnInfo(name = "number")
    var number: String
)