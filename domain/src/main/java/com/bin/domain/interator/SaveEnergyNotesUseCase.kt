package com.bin.domain.interator

import com.bin.domain.model.EnergyNote
import com.bin.domain.repository.EnergyNoteRepository
import javax.inject.Inject

class SaveEnergyNotesUseCase @Inject constructor(
    private val energyNoteRepository: EnergyNoteRepository
) {
    suspend operator fun invoke(energyNote: EnergyNote) = energyNoteRepository.saveNote(energyNote)
}