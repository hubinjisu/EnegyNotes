package com.bin.data.database.dao

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.bin.data.dao.EnergyNoteDao
import com.bin.data.database.NotesDatabase
import com.bin.data.model.EnergyNoteEntity
import com.bin.domain.model.EnergyType
import com.google.common.truth.Truth.assertThat
import java.time.LocalDate
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith

//@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
@SmallTest
class EnergyBiteDaoTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    private lateinit var database: NotesDatabase
    private lateinit var energyNoteDao: EnergyNoteDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Application>(),
            NotesDatabase::class.java
        ).allowMainThreadQueries().build()
        energyNoteDao = database.energyNoteDao()
    }


    @ExperimentalCoroutinesApi
    @Test
    fun test_save() {
        runBlockingTest {
            // given
            val givenEnergyNote = EnergyNoteEntity(10, LocalDate.now(), EnergyType.ELECTRICITY)
            // when
            energyNoteDao.save(givenEnergyNote)
            // then
            val result = energyNoteDao.getNotes()
            assertThat(result).isEqualTo(listOf(givenEnergyNote))
        }
    }

    @ExperimentalCoroutinesApi
    @After
    fun clearTestData() {
        runBlockingTest {
            energyNoteDao.deleteAll()
            database.close()
        }
    }
}