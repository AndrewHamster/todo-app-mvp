package com.homiak.andrii.todomvp.data.sources.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.homiak.andrii.todomvp.data.Task

/**
 * Created by Andre on 12-Jan-18.
 */
@Database(entities = [Task::class], version = 1)
abstract class AppDatabase: RoomDatabase()
{
    companion object {
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase
        {
            if(INSTANCE == null)
            {
                INSTANCE = Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
            }
            return INSTANCE!!
        }

    }

    abstract fun taskDao(): TaskDao
}