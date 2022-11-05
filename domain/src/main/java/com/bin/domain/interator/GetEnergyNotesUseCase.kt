package com.bin.domain.interator

import com.bin.domain.model.EnergyNote
import com.bin.domain.repository.EnergyNoteRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetEnergyNotesUseCase @Inject constructor(
    private val energyNoteRepository: EnergyNoteRepository
) {
    suspend operator fun invoke(): Flow<List<EnergyNote>> =
        energyNoteRepository.getEnergyNotes()
}