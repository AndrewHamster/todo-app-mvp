package com.homiak.andrii.todomvp.data

/**
 * Created by Andre on 06-Jan-18.
 */
interface TaskDataSource {
    fun getTasks(callback: LoadTasksCallback)

    interface LoadTasksCallback{
        fun onTasksLoaded(tasks: List<Task>)
    }
}