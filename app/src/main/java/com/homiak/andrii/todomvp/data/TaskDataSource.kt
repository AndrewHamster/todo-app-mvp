package com.homiak.andrii.todomvp.data

/**
 * Created by Andre on 06-Jan-18.
 */
interface TaskDataSource {
    fun getTasks(callback: LoadTasksCallback)

    fun getTask(id: Int, callback: LoadTaskCallback)


    interface LoadTasksCallback{
        fun onTasksLoaded(tasks: List<Task>)
    }

    interface LoadTaskCallback{
        fun onTaskLoaded(task: Task)

        fun onTaskNoFound()
    }
}