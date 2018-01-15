package com.homiak.andrii.todomvp.addtask

import com.homiak.andrii.todomvp.data.Task
import com.homiak.andrii.todomvp.data.TaskDataSource
import java.util.*

/**
 * Created by Andre on 13-Jan-18.
 */
class AddTaskPresenter(private val repository: TaskDataSource, private val view: AddTaskContract.View): AddTaskContract.Presenter {
    override fun start() {

    }

    private val isValid: Boolean
        get() = taskTitle.isNotBlank()

    private var isLoading = false

    override fun handleSaveClick() {
        if(isValid && !isLoading)
        {
            isLoading = true
            val task = Task(taskTitle, taskDescription, taskDueDate)
            repository.saveTask(task, object : TaskDataSource.SaveTaskCallback{
                override fun onTaskSaved() {
                    if(view.isActive()) view.showTaskSaved()
                    isLoading = false
                }

                override fun onError() {
                    if(view.isActive()) view.showSaveError("Error")
                    isLoading = false
                }
            })
        }

    }

    override var taskTitle: String = ""


    override var taskDescription: String = ""


    override var taskDueDate: Date = Calendar.getInstance().time

}