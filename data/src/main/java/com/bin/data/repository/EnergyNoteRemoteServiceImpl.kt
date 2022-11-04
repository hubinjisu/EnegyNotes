package com.bin.data.repository

import com.bin.data.model.EnergyNoteRemoteEntity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import timber.log.Timber

class EnergyNoteRemoteServiceImpl {
    private var database: DatabaseReference =
        Firebase.database("https://energynotes-babf2-default-rtdb.europe-west1.firebasedatabase.app/").reference

    private var data: EnergyNoteRemoteEntity? = null

    init {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                data = snapshot.getValue<EnergyNoteRemoteEntity>()
                Timber.d("Value is: $data")
            }

            override fun onCancelled(error: DatabaseError) {
                Timber.e("Failed to read value.", error.toException())
            }
        })
    }

    fun saveNotes(energyNoteRemoteEntity: EnergyNoteRemoteEntity) {
        database.child("notes").setValue(energyNoteRemoteEntity)

    }

    fun getNotes(): EnergyNoteRemoteEntity? {
        return data
    }
}