package com.bin.domain.interator

import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import com.bin.domain.repository.EnergyNoteRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetEnergyNotesByTypeUseCase @Inject constructor(
    private val energyNoteRepository: EnergyNoteRepository
) {
    suspend operator fun invoke(type: EnergyType): Flow<Result<List<EnergyNote>>> =
        energyNoteRepository.getEnergyNotesByType(type)
}