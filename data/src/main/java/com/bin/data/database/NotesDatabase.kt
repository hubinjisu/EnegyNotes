package com.bin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bin.data.converter.BigDecimalTypeConverter
import com.bin.data.converter.EnergyTypeConverter
import com.bin.data.converter.LocalDateConverter
import com.bin.data.dao.EnergyNoteDao
import com.bin.data.dao.UserDao
import com.bin.data.model.EnergyNoteEntity
import com.bin.data.model.UserEntity

@Database(
    entities = [EnergyNoteEntity::class, UserEntity::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(LocalDateConverter::class, EnergyTypeConverter::class, BigDecimalTypeConverter::class)
abstract class NotesDatabase : RoomDatabase() {

    internal abstract fun energyNoteDao(): EnergyNoteDao

    internal abstract fun userDao(): UserDao
}