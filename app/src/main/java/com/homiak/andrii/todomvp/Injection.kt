package com.homiak.andrii.todomvp

import android.content.Context
import com.homiak.andrii.todomvp.data.TaskRepository
import com.homiak.andrii.todomvp.data.sources.local.AppDatabase
import com.homiak.andrii.todomvp.data.sources.local.LocalTaskDataSource

/**
 * Created by Andre on 12-Jan-18.
 */
class Injection {
    companion object {
        fun provideTaskRepository(context: Context): TaskRepository
                = TaskRepository.getInstance(
                    LocalTaskDataSource.getInstance(
                            AppDatabase.getInstance(context)
                                    .taskDao()
                    )
                )
    }
}