package com.depogramming.kmpdemo.data.local

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

actual fun getDatabaseBuilder(): RoomDatabase.Builder<AppDatabase> {


    val context = checkNotNull(appContext) { "Application context is not initialized" }
    val dbFile = context.getDatabasePath("app.db")

    return Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        dbFile.absolutePath
    ).setDriver(BundledSQLiteDriver())
}

lateinit var appContext: Context

fun setApplicationContext(context: Context) {
    appContext = context
}