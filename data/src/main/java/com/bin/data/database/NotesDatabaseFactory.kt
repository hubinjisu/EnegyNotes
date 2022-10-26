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
            """INSERT INTO energy_note ('reading', 'recordDate', 'type', 'user_id') 
            |VALUES (45, '2020-08-01', 'WATER', 1), (59, '2020-09-02', 'WATER', 1), (71, '2020-10-01', 'WATER', 1),
            |(77, '2020-11-01', 'WATER', 1), (82, '2020-12-01', 'WATER', 1), (87, '2021-01-04', 'WATER', 1),
            |(91, '2021-02-01', 'WATER', 1), (96, '2021-03-04', 'WATER', 1), (101, '2021-04-07', 'WATER', 1),
            |(106, '2021-05-10', 'WATER', 1), (110, '2021-06-02', 'WATER', 1), (116, '2021-07-05', 'WATER', 1),
            |(121, '2021-08-04', 'WATER', 1), (126, '2021-09-02', 'WATER', 1), (131, '2021-10-05', 'WATER', 1),
            |(135, '2021-11-01', 'WATER', 1), (140, '2021-12-03', 'WATER', 1), (145, '2022-01-01', 'WATER', 1),
            |(149, '2022-02-01', 'WATER', 1), (154, '2022-03-05', 'WATER', 1), (159, '2022-04-07', 'WATER', 1),
            |(163, '2022-05-06', 'WATER', 1), (167, '2022-06-03', 'WATER', 1), (171, '2022-06-28', 'WATER', 1),
            |(176, '2022-07-27', 'WATER', 1)""".trimMargin()
        )

        db.execSQL(
            """INSERT INTO energy_note ('reading', 'recordDate', 'type', 'user_id') 
            |VALUES (402, '2020-08-01', 'ELECTRICITY', 1), (588, '2020-09-02', 'ELECTRICITY', 1), (757, '2020-10-01', 'ELECTRICITY', 1),
            |(916, '2020-11-01', 'ELECTRICITY', 1), (1098, '2020-12-01', 'ELECTRICITY', 1), (1304, '2021-01-04', 'ELECTRICITY', 1),
            |(1459, '2021-02-01', 'ELECTRICITY', 1), (1643, '2021-03-04', 'ELECTRICITY', 1), (1829, '2021-04-07', 'ELECTRICITY', 1),
            |(2011, '2021-05-10', 'ELECTRICITY', 1), (2135, '2021-06-02', 'ELECTRICITY', 1), (2300, '2021-07-05', 'ELECTRICITY', 1),
            |(2475, '2021-08-04', 'ELECTRICITY', 1), (2650, '2021-09-02', 'ELECTRICITY', 1), (2842, '2021-10-05', 'ELECTRICITY', 1),
            |(2978, '2021-11-01', 'ELECTRICITY', 1), (3164, '2021-12-03', 'ELECTRICITY', 1), (3334, '2022-01-01', 'ELECTRICITY', 1),
            |(3524, '2022-02-01', 'ELECTRICITY', 1), (3725, '2022-03-05', 'ELECTRICITY', 1), (3917, '2022-04-07', 'ELECTRICITY', 1),
            |(4090, '2022-05-06', 'ELECTRICITY', 1), (4257, '2022-06-03', 'ELECTRICITY', 1), (4399, '2022-06-28', 'ELECTRICITY', 1),
            |(4561, '2022-07-27', 'ELECTRICITY', 1)""".trimMargin()
        )

        db.execSQL(
            """INSERT INTO energy_note ('reading', 'recordDate', 'type', 'user_id') 
            |VALUES (16670, '2020-08-01', 'GAS', 1), (16711, '2020-09-02', 'GAS', 1), (16756, '2020-10-01', 'GAS', 1),
            |(16834, '2020-11-01', 'GAS', 1), (16947, '2020-12-01', 'GAS', 1), (17119, '2021-01-04', 'GAS', 1),
            |(17274, '2021-02-01', 'GAS', 1), (17411, '2021-03-04', 'GAS', 1), (17503, '2021-04-07', 'GAS', 1),
            |(17557, '2021-05-10', 'GAS', 1), (17585, '2021-06-02', 'GAS', 1), (17619, '2021-07-05', 'GAS', 1),
            |(17649, '2021-08-04', 'GAS', 1), (17677, '2021-09-02', 'GAS', 1), (17714, '2021-10-05', 'GAS', 1),
            |(17775, '2021-11-01', 'GAS', 1), (17881, '2021-12-03', 'GAS', 1), (17988, '2022-01-01', 'GAS', 1),
            |(18100, '2022-02-01', 'GAS', 1), (18193, '2022-03-05', 'GAS', 1), (18245, '2022-04-07', 'GAS', 1),
            |(18277, '2022-05-06', 'GAS', 1), (18303, '2022-06-03', 'GAS', 1), (18324, '2022-06-28', 'GAS', 1),
            |(18346, '2022-07-27', 'GAS', 1)""".trimMargin()
        )
    }
}