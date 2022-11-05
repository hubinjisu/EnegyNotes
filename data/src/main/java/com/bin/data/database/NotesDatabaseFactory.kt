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
        db.execSQL(
            """INSERT INTO user ('id', 'username') VALUES (1, 'Family Hu')""".trimMargin()
        )

        db.execSQL(
            """INSERT INTO energy_note ('reading', 'recordDate', 'type') 
            |VALUES (45, '2020-08-01', 'WATER'), (59, '2020-09-02', 'WATER'), (71, '2020-10-01', 'WATER'),
            |(77, '2020-11-01', 'WATER'), (82, '2020-12-01', 'WATER'), (87, '2021-01-04', 'WATER'),
            |(91, '2021-02-01', 'WATER'), (96, '2021-03-04', 'WATER'), (101, '2021-04-07', 'WATER'),
            |(106, '2021-05-10', 'WATER'), (110, '2021-06-02', 'WATER'), (116, '2021-07-05', 'WATER'),
            |(121, '2021-08-04', 'WATER'), (126, '2021-09-02', 'WATER'), (131, '2021-10-05', 'WATER'),
            |(135, '2021-11-01', 'WATER'), (140, '2021-12-03', 'WATER'), (145, '2022-01-01', 'WATER'),
            |(149, '2022-02-01', 'WATER'), (154, '2022-03-05', 'WATER'), (159, '2022-04-07', 'WATER'),
            |(163, '2022-05-06', 'WATER'), (167, '2022-06-03', 'WATER'), (171, '2022-06-28', 'WATER'),
            |(176, '2022-07-27', 'WATER'), (182, '2022-08-30', 'WATER'), (187, '2022-09-29', 'WATER'),
            |(191, '2022-10-28', 'WATER')""".trimMargin()
        )

        db.execSQL(
            """INSERT INTO energy_note ('reading', 'recordDate', 'type') 
            |VALUES (402, '2020-08-01', 'ELECTRICITY'), (588, '2020-09-02', 'ELECTRICITY'), (757, '2020-10-01', 'ELECTRICITY'),
            |(916, '2020-11-01', 'ELECTRICITY'), (1098, '2020-12-01', 'ELECTRICITY'), (1304, '2021-01-04', 'ELECTRICITY'),
            |(1459, '2021-02-01', 'ELECTRICITY'), (1643, '2021-03-04', 'ELECTRICITY'), (1829, '2021-04-07', 'ELECTRICITY'),
            |(2011, '2021-05-10', 'ELECTRICITY'), (2135, '2021-06-02', 'ELECTRICITY'), (2300, '2021-07-05', 'ELECTRICITY'),
            |(2475, '2021-08-04', 'ELECTRICITY'), (2650, '2021-09-02', 'ELECTRICITY'), (2842, '2021-10-05', 'ELECTRICITY'),
            |(2978, '2021-11-01', 'ELECTRICITY'), (3164, '2021-12-03', 'ELECTRICITY'), (3334, '2022-01-01', 'ELECTRICITY'),
            |(3524, '2022-02-01', 'ELECTRICITY'), (3725, '2022-03-05', 'ELECTRICITY'), (3917, '2022-04-07', 'ELECTRICITY'),
            |(4090, '2022-05-06', 'ELECTRICITY'), (4257, '2022-06-03', 'ELECTRICITY'), (4399, '2022-06-28', 'ELECTRICITY'),
            |(4561, '2022-07-27', 'ELECTRICITY'), (4764, '2022-08-30', 'ELECTRICITY'), (4935, '2022-09-29', 'ELECTRICITY'),
            |(5077, '2022-10-28', 'ELECTRICITY')""".trimMargin()
        )

        db.execSQL(
            """INSERT INTO energy_note ('reading', 'recordDate', 'type') 
            |VALUES (16670, '2020-08-01', 'GAS'), (16711, '2020-09-02', 'GAS'), (16756, '2020-10-01', 'GAS'),
            |(16834, '2020-11-01', 'GAS'), (16947, '2020-12-01', 'GAS'), (17119, '2021-01-04', 'GAS'),
            |(17274, '2021-02-01', 'GAS'), (17411, '2021-03-04', 'GAS'), (17503, '2021-04-07', 'GAS'),
            |(17557, '2021-05-10', 'GAS'), (17585, '2021-06-02', 'GAS'), (17619, '2021-07-05', 'GAS'),
            |(17649, '2021-08-04', 'GAS'), (17677, '2021-09-02', 'GAS'), (17714, '2021-10-05', 'GAS'),
            |(17775, '2021-11-01', 'GAS'), (17881, '2021-12-03', 'GAS'), (17988, '2022-01-01', 'GAS'),
            |(18100, '2022-02-01', 'GAS'), (18193, '2022-03-05', 'GAS'), (18245, '2022-04-07', 'GAS'),
            |(18277, '2022-05-06', 'GAS'), (18303, '2022-06-03', 'GAS'), (18324, '2022-06-28', 'GAS'),
            |(18346, '2022-07-27', 'GAS'), (18368, '2022-08-30', 'GAS'), (18381, '2022-09-29', 'GAS'),
            |(18392, '2022-10-28', 'GAS')""".trimMargin()
        )
    }
}