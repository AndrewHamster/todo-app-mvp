package com.homiak.andrii.todomvp.data

import com.homiak.andrii.todomvp.data.sources.local.LocalTaskDataSource

/**
 * Created by Andre on 06-Jan-18.
 */
class TaskRepository private constructor(private val localTaskDataSource: TaskDataSource): TaskDataSource
{

    override fun saveTask(task: Task, callback: TaskDataSource.SaveTaskCallback) {
        localTaskDataSource.saveTask(task, callback)
    }

    override fun getTasks(callback: TaskDataSource.LoadTasksCallback) {
        localTaskDataSource.getTasks(callback)
    }

    override fun getTask(id: Int, callback: TaskDataSource.LoadTaskCallback) {
        localTaskDataSource.getTask(id, callback)
    }

    companion object {
        private var INSTANCE: TaskRepository? = null
        fun getInstance(localTaskDataSource: LocalTaskDataSource): TaskRepository {
            if(INSTANCE == null)
            {
                INSTANCE = TaskRepository(localTaskDataSource)
            }
            return INSTANCE!!
        }
    }
}