package com.example.androidtemplate.fragments.room_database.database

import androidx.room.*
import androidx.room.Dao

@Dao
interface UserDao {
    @Query("SELECT * FROM tbl_users")
    fun getUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(userEntity: UserEntity): Long

    @Update
    fun updateUser(userEntity: UserEntity)

    @Delete
    fun deleteUser(userEntity: UserEntity)
}