package com.homiak.andrii.todomvp.tasks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.homiak.andrii.todomvp.R
import com.homiak.andrii.todomvp.data.Task

class TasksFragment : Fragment(), TasksContract.View {

    private lateinit var tasksPresenter: TasksContract.Presenter

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater!!.inflate(R.layout.fragment_tasks, container, false)
    }

    override fun setPresenter(presenter: TasksContract.Presenter) {
        tasksPresenter = presenter
    }

    override fun showTasksEmpty() {
        Toast.makeText(activity, "No tasks!", Toast.LENGTH_LONG).show()
    }

    override fun showTasks(list: List<Task>) {
        Toast.makeText(activity, "Loaded ${list.size} tasks!", Toast.LENGTH_LONG).show()
    }

    override fun onResume() {
        tasksPresenter.start()
        super.onResume()
    }

    companion object {
        fun newInstance(): TasksFragment {
            val fragment = TasksFragment()
            return fragment
        }
    }
}
