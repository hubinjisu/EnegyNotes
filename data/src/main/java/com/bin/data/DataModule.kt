package com.bin.data

import android.content.Context
import com.bin.data.dao.EnergyNoteDao
import com.bin.data.dao.UserDao
import com.bin.data.database.NotesDatabase
import com.bin.data.database.NotesDatabaseFactory
import com.bin.data.mapper.EnergyNoteEntityMapper
import com.bin.data.repository.EnergyNoteRepositoryImpl
import com.bin.domain.repository.EnergyNoteRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {

    @Binds
    abstract fun bindEnergyNoteRepository(impl: EnergyNoteRepositoryImpl): EnergyNoteRepository

    companion object {
        @Provides
        fun provideIoDispatcher() = Dispatchers.IO

        @Provides
        fun provideNotesDatabaseFactory(@ApplicationContext context: Context): NotesDatabaseFactory =
            NotesDatabaseFactory(context)

        @Provides
        fun provideMTDatabase(notesDatabaseFactory: NotesDatabaseFactory): NotesDatabase =
            notesDatabaseFactory.createDatabase()

        @Provides
        internal fun provideEnergyNoteDao(db: NotesDatabase): EnergyNoteDao = db.energyNoteDao()

        @Provides
        internal fun provideUserDao(db: NotesDatabase): UserDao = db.userDao()
    }
}