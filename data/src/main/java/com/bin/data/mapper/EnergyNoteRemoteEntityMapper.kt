package com.bin.data.mapper

import com.bin.data.converter.EnergyTypeConverter
import com.bin.data.converter.LocalDateConverter
import com.bin.data.model.EnergyNoteRemoteEntity
import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import java.time.LocalDate
import javax.inject.Inject

class EnergyNoteRemoteEntityMapper @Inject constructor() {

    fun mapFromEntity(entity: EnergyNoteRemoteEntity): EnergyNote = with(entity) {
        EnergyNote(
            reading = reading ?: 0,
            recordDate = LocalDateConverter.toLocalDate(recordDate) ?: LocalDate.now(),
            type = type?.let { EnergyTypeConverter().stringToEnergyType(it) } ?: EnergyType.GAS
        )
    }

    fun mapToEntity(note: EnergyNote): EnergyNoteRemoteEntity = with(note) {
        EnergyNoteRemoteEntity(
            reading = reading,
            recordDate = LocalDateConverter.fromLocalDate(recordDate),
            type = EnergyTypeConverter().energyTypeToString(type)
        )
    }
}