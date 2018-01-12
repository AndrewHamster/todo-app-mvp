package com.homiak.andrii.todomvp.taskdetail

import com.homiak.andrii.todomvp.data.Task
import com.homiak.andrii.todomvp.data.TaskDataSource
import com.homiak.andrii.todomvp.data.TaskRepository

/**
 * Created by Andre on 09-Jan-18.
 */
class TaskDetailPresenter(val view: TaskDetailContract.View, private val repository: TaskRepository, val id: Int): TaskDetailContract.Presenter {
    override fun start() {
        loadTaskInfo()
    }

    override fun deleteTask() {
        //todo
    }

    override fun markAsCompleted() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun loadTaskInfo() {
        repository.getTask(id, object : TaskDataSource.LoadTaskCallback
        {
            override fun onTaskLoaded(task: Task) {
                if(view.isActive())
                    view.showTaskInfo(task)
            }

            override fun onTaskNoFound() {
                //todo
            }
        })
    }
}