package com.bin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bin.domain.interator.SaveEnergyNotesUseCase
import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import com.bin.presentation.RouteNavigator
import com.bin.presentation.mapper.EnergyNoteViewMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.ZonedDateTime
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class RecordViewModel @Inject constructor(
    private val saveEnergyNotesUseCase: SaveEnergyNotesUseCase,
    private val routeNavigator: RouteNavigator
) : ViewModel(), RouteNavigator by routeNavigator {

    fun recordWaterNote(reading: Long) {
        recordEnergyNote(reading, EnergyType.WATER)
    }

    fun recordGasNote(reading: Long) {
        recordEnergyNote(reading, EnergyType.GAS)
    }

    fun recordElectricityNote(reading: Long) {
        recordEnergyNote(reading, EnergyType.ELECTRICITY)
    }

    private fun recordEnergyNote(reading: Long, type: EnergyType) {
        viewModelScope.launch {
            saveEnergyNotesUseCase.invoke(
                EnergyNote(
                    reading = reading,
                    recordDate = ZonedDateTime.now(),
                    type = type
                )
            )
        }
    }
}