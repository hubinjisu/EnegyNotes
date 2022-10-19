package com.bin.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bin.domain.interator.GetEnergyNotesByTypeUseCase
import com.bin.domain.interator.SaveEnergyNotesUseCase
import com.bin.domain.model.EnergyType
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
class NotesViewModel @Inject constructor(
    private val routeNavigator: RouteNavigator,
    private val getEnergyNotesByTypeUseCase: GetEnergyNotesByTypeUseCase,
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
            getEnergyNotesByTypeUseCase.invoke(EnergyType.ELECTRICITY).collectLatest { result ->
                _energyNotes.value = result.getOrDefault(emptyList()).map {
                    energyNoteViewMapper.mapToEnergyNoteView(it)
                }
            }
        }
    }
}