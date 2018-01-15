package com.homiak.andrii.todomvp.addtask

import com.homiak.andrii.todomvp.BasePresenter
import com.homiak.andrii.todomvp.BaseView
import java.util.*

/**
 * Created by Andre on 13-Jan-18.
 */
interface AddTaskContract {
    interface View: BaseView<Presenter>
    {
        fun showTitleError(err: String)
        fun showDescriptionError(err: String)
        fun showTaskSaved()
        fun showSaveError(err: String)
    }

    interface Presenter: BasePresenter
    {
        fun handleSaveClick()
        var taskTitle: String
        var taskDescription: String
        var taskDueDate: Date
    }
}