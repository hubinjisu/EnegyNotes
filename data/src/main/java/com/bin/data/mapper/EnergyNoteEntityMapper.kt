package com.bin.data.mapper

import com.bin.data.model.EnergyNoteEntity
import com.bin.domain.model.EnergyNote
import javax.inject.Inject

class EnergyNoteEntityMapper @Inject constructor() {

    fun mapFromEntity(entity: EnergyNoteEntity): EnergyNote = with(entity) {
        EnergyNote(
            reading = reading,
            recordDate = recordDate,
            type = type
        )
    }

    fun mapToEntity(note: EnergyNote): EnergyNoteEntity = with(note) {
        EnergyNoteEntity(
            reading = reading,
            recordDate = recordDate,
            type = type
        )
    }
}