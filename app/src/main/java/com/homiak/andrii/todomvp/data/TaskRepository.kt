package com.homiak.andrii.todomvp.data

import android.os.Handler
import java.util.*

/**
 * Created by Andre on 06-Jan-18.
 */
class TaskRepository private constructor(): TaskDataSource
{
    override fun getTasks(callback: TaskDataSource.LoadTasksCallback) {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_MONTH, 2)
        val tasks = TASKS
        Handler().postDelayed({
            callback.onTasksLoaded(tasks)
        }, 3000)
    }

    override fun getTask(id: Int, callback: TaskDataSource.LoadTaskCallback) {
        Handler().postDelayed({
            callback.onTaskLoaded(TASKS.find { it.id == id }!!)
        }, 3000)
    }

    companion object {
        private val calendar: Calendar = Calendar.getInstance().apply { add(Calendar.WEEK_OF_MONTH, 2) }
        private val TASKS: MutableList<Task> by lazy { mutableListOf(
                Task(0, "TODO", "Do some stuff", calendar.time),
                Task(1, "Stuff", "Do some stuff", calendar.time),
                Task(2, "Pizza time!", "Do some stuff", calendar.time),
                Task(3, "Play zelda", "Do some stuff", calendar.time),
                Task(4, "Beat DarkSouls", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", calendar.time)
            )
        }

        val instance by lazy { TaskRepository() }
    }
}