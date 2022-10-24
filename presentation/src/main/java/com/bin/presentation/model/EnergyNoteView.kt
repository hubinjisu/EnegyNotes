package com.bin.presentation.model

import java.math.BigDecimal
import java.time.LocalDate

data class EnergyNoteView(
    val reading: BigDecimal,
    val recordDate: LocalDate,
    val energyType: EnergyTypeView,
    val cost: String,
    val totalCost: String
)