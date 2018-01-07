package com.homiak.andrii.todomvp.data

import android.os.Handler
import java.util.*

/**
 * Created by Andre on 06-Jan-18.
 */
class TaskRepository: TaskDataSource
{
    override fun getTasks(callback: TaskDataSource.LoadTasksCallback) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_MONTH, 2)
        val tasks = listOf(Task("TODO", "Create todo app", calendar.time))
        val handler = Handler()
        handler.postDelayed({
            callback.onTasksLoaded(tasks)
        }, 3000)
    }
}