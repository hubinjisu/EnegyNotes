package com.bin.domain.repository

import androidx.annotation.WorkerThread
import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import kotlinx.coroutines.flow.Flow

interface EnergyNoteRepository {

    suspend fun getEnergyNotes(): Flow<Result<List<EnergyNote>>>

    suspend fun getEnergyNotesByType(type: EnergyType): Flow<Result<List<EnergyNote>>>

    suspend fun saveNote(energyNote: EnergyNote)

    suspend fun deleteNote(energyNote: EnergyNote)
}