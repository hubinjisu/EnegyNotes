package com.bin.ui

import com.bin.presentation.model.EnergyNoteView
import com.bin.presentation.model.EnergyTypeView
import java.time.LocalDate

class EnergyNoteViewBuilder {

    private var reading: Long = 100
    private var recordDate: LocalDate = LocalDate.of(2022, 10, 10)
    private var energyType: EnergyTypeView = EnergyTypeView.GAS
    private var cost: String = "100 €"

    fun withReading(reading: Long) = apply { this.reading = reading }

    fun withRecordDate(recordDate: LocalDate) = apply { this.recordDate = recordDate }

    fun withEnergyType(energyType: EnergyTypeView) = apply { this.energyType = energyType }

    fun withCost(cost: String) = apply { this.cost = cost }

    fun build() = EnergyNoteView(reading, recordDate, energyType, cost)
}