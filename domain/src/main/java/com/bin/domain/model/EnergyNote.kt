package com.bin.domain.model

import java.time.LocalDate

data class EnergyNote(
    val reading: Long,
    val recordDate: LocalDate,
    val type: EnergyType
)
