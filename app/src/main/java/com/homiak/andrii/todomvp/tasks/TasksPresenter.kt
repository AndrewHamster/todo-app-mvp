package com.homiak.andrii.todomvp.tasks

import com.homiak.andrii.todomvp.data.Task
import com.homiak.andrii.todomvp.data.TaskDataSource
import com.homiak.andrii.todomvp.data.TaskRepository

class TasksPresenter(private val taskRepository: TaskRepository, private val view: TasksContract.View): TasksContract.Presenter
{
    private val tasks = mutableListOf<Task>()
    override fun start() {
        loadTasks()
    }

    override fun loadTasks() {
        tasks.clear()
        taskRepository.getTasks(object : TaskDataSource.LoadTasksCallback {
            override fun onTasksLoaded(tasks: List<Task>) {
                if(view.isActive())
                {
                    if(tasks.any())
                        view.showTasks(tasks)
                    else
                        view.showTasksEmpty()

                }
            }
        })
    }
}