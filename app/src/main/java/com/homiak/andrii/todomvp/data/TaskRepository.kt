package com.homiak.andrii.todomvp.data

import java.util.*

/**
 * Created by Andre on 06-Jan-18.
 */
class TaskRepository: TaskDataSource
{
    override fun getTasks(): List<Task> {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_MONTH, 2)
        return listOf(Task("TODO", "Create todo app", calendar.time))
    }
}