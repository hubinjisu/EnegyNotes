package com.bin.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.bin.data.converter.EnergyTypeConverter
import com.bin.data.converter.ZonedDateTimeConverter
import com.bin.data.dao.EnergyNoteDao
import com.bin.data.model.EnergyNoteEntity

@Database(entities = [EnergyNoteEntity::class], version = 1, exportSchema = true)
@TypeConverters(ZonedDateTimeConverter::class, EnergyTypeConverter::class)
abstract class NotesDatabase: RoomDatabase() {

    internal abstract fun energyNoteDao(): EnergyNoteDao
}