package com.bin.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.bin.domain.model.EnergyType
import java.time.LocalDate

@Entity(
    tableName = "energy_note",
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("user_id"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class EnergyNoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Long = 1,
    @ColumnInfo(name = "reading")
    val reading: Long,
    @ColumnInfo(name = "recordDate")
    val recordDate: LocalDate,
    @ColumnInfo(name = "type")
    val type: EnergyType,
    @ColumnInfo(name = "user_id")
    val userId: Long = 1
)
