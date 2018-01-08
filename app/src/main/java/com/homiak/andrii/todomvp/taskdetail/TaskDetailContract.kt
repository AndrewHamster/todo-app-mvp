package com.homiak.andrii.todomvp.taskdetail

import com.homiak.andrii.todomvp.BasePresenter
import com.homiak.andrii.todomvp.BaseView
import com.homiak.andrii.todomvp.data.Task

/**
 * Created by Andre on 08-Jan-18.
 */
interface TaskDetailContract {
    interface View : BaseView<Presenter>
    {
        fun showTaskDeleted()
        fun showTaskInfo(task: Task)
    }

    interface Presenter: BasePresenter
    {
        fun deleteTask()
        fun markAsCompleted()
    }
}