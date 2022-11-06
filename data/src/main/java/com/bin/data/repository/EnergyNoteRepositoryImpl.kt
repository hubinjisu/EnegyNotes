package com.bin.data.repository

import androidx.annotation.WorkerThread
import com.bin.data.dao.EnergyNoteDao
import com.bin.data.mapper.EnergyNoteEntityMapper
import com.bin.data.mapper.EnergyNoteRemoteEntityMapper
import com.bin.data.model.EnergyNoteRemoteEntity
import com.bin.domain.model.EnergyNote
import com.bin.domain.model.EnergyType
import com.bin.domain.repository.EnergyNoteRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.suspendCancellableCoroutine
import timber.log.Timber

class EnergyNoteRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val energyNoteDao: EnergyNoteDao,
    private val energyNoteEntityMapper: EnergyNoteEntityMapper,
    private val energyNoteRemoteEntityMapper: EnergyNoteRemoteEntityMapper
) : EnergyNoteRepository {

    private var remoteDatabase: DatabaseReference = Firebase.database(REMOTE_DATABASE_URL).reference
//    private var user: String = userDao.getUsers()[0].username

    override suspend fun getEnergyNotes(): Flow<List<EnergyNote>> = flow {
//        emit(Result.success(energyNoteDao.getNotes().map { energyNoteEntityMapper.mapFromEntity(it) }))
        emit(getRemoteEnergyNotes())
    }.distinctUntilChanged { old, new ->
        old == new
    }.flowOn(dispatcher)

    private suspend fun getRemoteEnergyNotes(): List<EnergyNote> =
        suspendCancellableCoroutine { continuation ->
            remoteDatabase.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    snapshot.child(USER).child(DATA_NOTES).getValue<Map<String, EnergyNoteRemoteEntity>>()?.let {
                        if (continuation.isActive) {
                            continuation.resumeWith(Result.success(it.values.map { remoteEntity ->
                                energyNoteRemoteEntityMapper.mapFromEntity(
                                    remoteEntity
                                )
                            }.sortedBy { energyNote -> energyNote.recordDate }))
                        }
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    Timber.e("Failed to read value.", error.toException())
                    if (continuation.isActive) {
                        continuation.resumeWith(Result.failure(error.toException()))
                    }
                }
            })
        }

    override suspend fun getEnergyNotesByType(type: EnergyType): Flow<Result<List<EnergyNote>>> = flow {
        emit(Result.success(energyNoteDao.getNotesByType(type).map { energyNoteEntityMapper.mapFromEntity(it) }))
    }.flowOn(dispatcher)

    @WorkerThread
    override suspend fun saveNote(energyNote: EnergyNote) {
//        val energyNoteEntity = energyNoteEntityMapper.mapToEntity(energyNote)
//        energyNoteDao.save(energyNoteEntity)

        val energyNoteRemoteEntity = energyNoteRemoteEntityMapper.mapToEntity(energyNote)
        remoteDatabase
            .child(USER)
            .child(DATA_NOTES)
            .child(energyNoteRemoteEntity.toRemoteEntityKey())
            .setValue(
                EnergyNoteRemoteEntity(
                    energyNoteRemoteEntity.reading,
                    energyNoteRemoteEntity.recordDate,
                    energyNoteRemoteEntity.type
                )
            )
    }

    private fun EnergyNoteRemoteEntity.toRemoteEntityKey() = "$recordDate $type"

    @WorkerThread
    override suspend fun deleteNote(energyNote: EnergyNote) {
        val energyNoteRemoteEntity = energyNoteRemoteEntityMapper.mapToEntity(energyNote)
        remoteDatabase
            .child(USER)
            .child(DATA_NOTES).child(energyNoteRemoteEntity.toRemoteEntityKey()).removeValue()
//        energyNoteDao.delete(energyNote.recordDate)
    }

    companion object {
        private const val REMOTE_DATABASE_URL =
            "https://energynotes-babf2-default-rtdb.europe-west1.firebasedatabase.app/"
        private const val USER = "Family Hu"
        private const val DATA_NOTES = "notes"
    }
}