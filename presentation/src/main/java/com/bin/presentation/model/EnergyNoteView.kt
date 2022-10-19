package com.bin.presentation.model

import java.time.ZonedDateTime

data class EnergyNoteView(
    val reading: Long,
    val recordDate: ZonedDateTime,
    val energyType: EnergyTypeView,
    val cost: String,
    val totalCost: String
)