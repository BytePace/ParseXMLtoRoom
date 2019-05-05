package com.bytepace.ipr

import android.app.Application
import android.arch.persistence.room.Room
import com.bytepace.ipr.database.AppDatabase
import java.lang.ref.WeakReference

class App: Application() {
    companion object {
        private lateinit var instance: WeakReference<App>
        lateinit var database: AppDatabase

        private const val DB_NAME = "RoomDB"

        fun get(): App? {
            return instance.get()
        }
    }

    override fun onCreate() {
        super.onCreate()
        instance = WeakReference(this)
        database = Room.databaseBuilder(this, AppDatabase::class.java, DB_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }
}