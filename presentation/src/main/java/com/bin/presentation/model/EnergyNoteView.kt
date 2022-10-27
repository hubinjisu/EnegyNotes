package com.bin.presentation.model

import java.time.LocalDate

data class EnergyNoteView(
    val reading: Long,
    val recordDate: LocalDate,
    val energyType: EnergyTypeView,
    val cost: String
)