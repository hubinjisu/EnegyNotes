package com.bin.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import androidx.sqlite.db.framework.FrameworkSQLiteOpenHelperFactory
import javax.inject.Inject
import timber.log.Timber

class NotesDatabaseFactory @Inject constructor(
    private val context: Context
) {
    fun createDatabase(): NotesDatabase = createDatabase(false)

    private fun createDatabase(allowMainThread: Boolean, databaseName: String = "notes.db"): NotesDatabase {
        val factory: SupportSQLiteOpenHelper.Factory = FrameworkSQLiteOpenHelperFactory()

        val builder = Room.databaseBuilder(context, NotesDatabase::class.java, databaseName)
            .addMigrations(*DatabaseMigrations.createMigrations())
            .openHelperFactory(factory)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) =
                    this@NotesDatabaseFactory.onCreate(db)

                override fun onOpen(db: SupportSQLiteDatabase) =
                    this@NotesDatabaseFactory.onOpen()

                override fun onDestructiveMigration(db: SupportSQLiteDatabase) =
                    this@NotesDatabaseFactory.onDestructiveMigration()
            })

        // For Unittests
        if (allowMainThread) {
            builder.allowMainThreadQueries()
        }

        return builder.build()
    }

    private fun onOpen() {
        Timber.d("onOpen()")
    }

    private fun onCreate(db: SupportSQLiteDatabase) {
        Timber.d("onCreate()")
        prepopulateTables(db)
    }

    private fun onDestructiveMigration() {
        Timber.d("onDestructiveMigration()")
    }

    /**
     * Befuellt die Datenbank initial mit Daten.
     */
    private fun prepopulateTables(db: SupportSQLiteDatabase) {
        db.execSQL("INSERT INTO energy_note ('reading', 'recordDate', 'type') VALUES (100, '2022-09-30', 'GAS')")
    }
}