package com.homiak.andrii.todomvp.tasks

import com.homiak.andrii.todomvp.BasePresenter
import com.homiak.andrii.todomvp.BaseView
import com.homiak.andrii.todomvp.data.Task

/**
 * Created by Andre on 06-Jan-18.
 */
interface TasksContract
{
    interface View : BaseView<Presenter>
    {
        fun showTasksEmpty()
        fun showTasks(list: List<Task>)
    }

    interface Presenter : BasePresenter
    {
        fun loadTasks()
    }
}