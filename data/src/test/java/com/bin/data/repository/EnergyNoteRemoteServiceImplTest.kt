package com.bin.data.repository

import com.bin.data.converter.LocalDateConverter
import com.bin.data.model.EnergyNoteEntity
import com.bin.data.model.EnergyNoteRemoteEntity
import com.bin.domain.model.EnergyType
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import org.junit.Assert.*

import org.junit.Test

class EnergyNoteRemoteServiceImplTest {
    private val sut = EnergyNoteRemoteServiceImpl()

    @Test
    fun saveNotes() {
        val note = EnergyNoteRemoteEntity("1", 100, "2022-11-1", "GAS")
        sut.saveNotes(note)
    }

    @Test
    fun getNotes() {

    }
}