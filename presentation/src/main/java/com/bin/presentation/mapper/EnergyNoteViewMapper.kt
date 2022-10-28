package com.bin.presentation.mapper

import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import com.bin.presentation.model.EnergyNoteView
import com.bin.presentation.model.EnergyTypeView
import java.math.BigDecimal
import java.math.RoundingMode
import javax.inject.Inject

class EnergyNoteViewMapper @Inject constructor() {

    fun mapToEnergyNoteView(note: EnergyNote): EnergyNoteView = with(note) {
        EnergyNoteView(
            reading = reading,
            recordDate = note.recordDate,
            energyType = EnergyTypeView.valueOf(note.type.name),
            cost = getCost(reading, type).toString()
        )
    }

    private fun getCost(reading: Long, type: EnergyType) = when (type) {
        EnergyType.WATER -> getWaterCost(reading)
        EnergyType.ELECTRICITY -> getElectricityCost(reading)
        EnergyType.GAS -> getGasCost(reading)
    }

    private fun getElectricityCost(reading: Long) =
        BigDecimal.valueOf(12.12 + 28.45 * reading / 100)
            .setScale(2, RoundingMode.HALF_UP)

    private fun getWaterCost(reading: Long) =
        BigDecimal.valueOf(59 / 12 + 4.82 * reading)
            .setScale(2, RoundingMode.HALF_UP)

    private fun getGasCost(reading: Long) =
        BigDecimal.valueOf(7.67 + 0.0993 * 10.90 * reading)
            .setScale(2, RoundingMode.HALF_UP)
}