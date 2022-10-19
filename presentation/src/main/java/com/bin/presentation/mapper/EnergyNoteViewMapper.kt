package com.bin.presentation.mapper

import com.bin.domain.model.EnergyNote
import com.bin.presentation.model.EnergyNoteView
import com.bin.presentation.model.EnergyTypeView
import javax.inject.Inject

class EnergyNoteViewMapper @Inject constructor() {

    fun mapToEnergyNoteView(note: EnergyNote): EnergyNoteView = with(note) {
        EnergyNoteView(
            reading = reading,
            recordDate = note.recordDate,
            energyType = EnergyTypeView.valueOf(note.type.name),
            cost = "cost",
            totalCost = "1233"
        )
    }
}