package com.bin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bin.domain.interator.SaveEnergyNotesUseCase
import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import com.bin.presentation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import java.math.BigDecimal
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val saveEnergyNotesUseCase: SaveEnergyNotesUseCase, private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {

    fun recordWaterNote(reading: BigDecimal?, recordDate: LocalDate) = reading?.let {
        recordEnergyNote(it, recordDate, EnergyType.WATER)
    }

    fun recordGasNote(reading: BigDecimal?, recordDate: LocalDate) = reading?.let {
        recordEnergyNote(it, recordDate, EnergyType.GAS)
    }

    fun recordElectricityNote(reading: BigDecimal?, recordDate: LocalDate) = reading?.let {
        recordEnergyNote(it, recordDate, EnergyType.ELECTRICITY)
    }

    private fun recordEnergyNote(reading: BigDecimal, recordDate: LocalDate, type: EnergyType) {
        viewModelScope.launch {
            saveEnergyNotesUseCase.invoke(
                EnergyNote(
                    reading = reading, recordDate = recordDate, type = type
                )
            )
        }
    }
}