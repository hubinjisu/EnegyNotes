package com.bin.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * [Room Database Migrations](https://developer.android.com/training/data-storage/room/migrating-db-versions)
 *
 */
internal object DatabaseMigrations {

    fun createMigrations() = migrations {
        migrate(1, 2) {

        }
    }

    private fun migrations(init: MutableList<Migration>.() -> Unit): Array<Migration> {
        val list = mutableListOf<Migration>()
        list.init()
        return list.toTypedArray()
    }

    private fun MutableList<Migration>.migrate(
        old: Int,
        new: Int,
        migrationFunction: (database: SupportSQLiteDatabase) -> Unit
    ) {
        val migration = object : Migration(old, new) {
            override fun migrate(database: SupportSQLiteDatabase) {
                migrationFunction(database)
            }
        }
        add(migration)
    }
}