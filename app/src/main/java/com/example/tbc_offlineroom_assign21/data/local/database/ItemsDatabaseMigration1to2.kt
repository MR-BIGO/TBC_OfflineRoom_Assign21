package com.example.tbc_offlineroom_assign21.data.local.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

object ItemsDatabaseMigration1to2 {

    val MIGRATION_1_2: Migration = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("ALTER TABLE items ADD COLUMN category TEXT NOT NULL DEFAULT ''")
        }
    }
}