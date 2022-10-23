package com.bin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bin.domain.interator.SaveEnergyNotesUseCase
import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import com.bin.presentation.RouteNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDate
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val saveEnergyNotesUseCase: SaveEnergyNotesUseCase,
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {

    fun recordWaterNote(reading: Long, recordDate: LocalDate) {
        recordEnergyNote(reading, recordDate, EnergyType.WATER)
    }

    fun recordGasNote(reading: Long, recordDate: LocalDate) {
        recordEnergyNote(reading, recordDate, EnergyType.GAS)
    }

    fun recordElectricityNote(reading: Long, recordDate: LocalDate) {
        recordEnergyNote(reading, recordDate, EnergyType.ELECTRICITY)
    }

    private fun recordEnergyNote(reading: Long, recordDate: LocalDate, type: EnergyType) {
        viewModelScope.launch {
            saveEnergyNotesUseCase.invoke(
                EnergyNote(
                    reading = reading,
                    recordDate = recordDate,
                    type = type
                )
            )
        }
    }
}