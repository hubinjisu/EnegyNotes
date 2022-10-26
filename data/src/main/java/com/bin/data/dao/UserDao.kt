package com.bin.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bin.data.model.EnergyNoteEntity
import com.bin.data.model.UserEntity
import com.bin.domain.model.EnergyType
import java.time.LocalDate

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(userEntity: UserEntity)

    @Query("select * from user")
    suspend fun getUsers(): List<UserEntity>

    @Query("DELETE FROM user")
    suspend fun deleteAll()

    @Query("DELETE FROM user where username = :username")
    suspend fun delete(username: String)
}