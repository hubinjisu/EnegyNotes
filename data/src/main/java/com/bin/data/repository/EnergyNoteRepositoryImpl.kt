package com.bin.data.repository

import androidx.annotation.WorkerThread
import com.bin.data.converter.EnergyTypeConverter
import com.bin.data.converter.LocalDateConverter
import com.bin.data.dao.EnergyNoteDao
import com.bin.data.mapper.EnergyNoteEntityMapper
import com.bin.data.model.EnergyNoteRemoteEntity
import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import com.bin.domain.repository.EnergyNoteRepository
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.UUID
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class EnergyNoteRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val energyNoteDao: EnergyNoteDao,
    private val energyNoteEntityMapper: EnergyNoteEntityMapper
) : EnergyNoteRepository {

    private val energyNoteRemoteService = EnergyNoteRemoteServiceImpl()

    override suspend fun getEnergyNotes(): Flow<Result<List<EnergyNote>>> = flow {
        emit(Result.success(energyNoteDao.getNotes().map { energyNoteEntityMapper.mapFromEntity(it) }))
    }.flowOn(dispatcher)


    override suspend fun getEnergyNotesByType(type: EnergyType): Flow<Result<List<EnergyNote>>> = flow {
        emit(Result.success(energyNoteDao.getNotesByType(type).map { energyNoteEntityMapper.mapFromEntity(it) }))
    }.flowOn(dispatcher)

    @WorkerThread
    override suspend fun saveNote(energyNote: EnergyNote) {
        energyNoteDao.save(energyNoteEntityMapper.mapToEntity(energyNote))
        energyNoteRemoteService.saveNotes(
            EnergyNoteRemoteEntity(
                UUID.randomUUID().toString(),
                energyNote.reading,
                LocalDateConverter.fromLocalDate(energyNote.recordDate) ?: "",
                EnergyTypeConverter().energyTypeToString(energyNote.type)
            )
        )
    }

    @WorkerThread
    override suspend fun deleteNote(energyNote: EnergyNote) {
        val database = Firebase.database
        val myRef = database.getReference("message")
        myRef.setValue("")
        energyNoteDao.delete(energyNote.recordDate)
    }
}