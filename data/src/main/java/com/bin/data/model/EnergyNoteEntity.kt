package com.bin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.bin.data.converter.EnergyTypeConverter
import com.bin.domain.model.EnergyType
import java.time.ZonedDateTime

@Entity(tableName = "energy_note")
@TypeConverters(EnergyTypeConverter::class)
data class EnergyNoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "reading")
    val reading: Long,
    @ColumnInfo(name = "timestamp")
    val timestamp: ZonedDateTime,
    @ColumnInfo(name = "type")
    val type: EnergyType
)
