package com.homiak.andrii.todomvp.tasks

import com.homiak.andrii.todomvp.data.Task
import com.homiak.andrii.todomvp.data.TaskDataSource
import com.homiak.andrii.todomvp.data.TaskRepository

/**
 * Created by Andre on 06-Jan-18.
 */
class TaskPresenter(private val taskRepository: TaskRepository, private val tasksView: TasksContract.View): TasksContract.Presenter
{
    private val tasks = mutableListOf<Task>()
    override fun start() {
        loadTasks()
    }

    override fun loadTasks() {
        tasks.clear()
        taskRepository.getTasks(object : TaskDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                tasksView.showTasks(tasks)
            }
        })
    }
}