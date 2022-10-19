package com.bin.domain.model

import java.time.ZonedDateTime

data class EnergyNote(
    val reading: Long,
    val recordDate: ZonedDateTime,
    val type: EnergyType
)
