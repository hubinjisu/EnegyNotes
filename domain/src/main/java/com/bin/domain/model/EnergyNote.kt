package com.bin.domain.model

import java.math.BigDecimal
import java.time.LocalDate

data class EnergyNote(
    val reading: BigDecimal,
    val recordDate: LocalDate,
    val type: EnergyType
)
