package com.bin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bin.domain.interator.GetEnergyNotesUseCase
import com.bin.presentation.RouteNavigator
import com.bin.presentation.mapper.EnergyNoteViewMapper
import com.bin.presentation.model.EnergyNoteView
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@HiltViewModel
class SummaryViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val getEnergyNotesUseCase: GetEnergyNotesUseCase,
    private val energyNoteViewMapper: EnergyNoteViewMapper
) : ViewModel(), RouteNavigator by routeNavigator {

    private val _energyNotes = MutableStateFlow(emptyList<EnergyNoteView>())

    val energyNotes: StateFlow<List<EnergyNoteView>>
        get() = _energyNotes

    init {
        initNotes()
    }

    private fun initNotes() {
        viewModelScope.launch {
            getEnergyNotesUseCase.invoke().collectLatest { result ->
                _energyNotes.value = result.map {
                    energyNoteViewMapper.mapToEnergyNoteView(it)
                }
            }
        }
    }
}