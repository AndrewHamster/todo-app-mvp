package com.homiak.andrii.todomvp.tasks

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.util.DiffUtil
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.homiak.andrii.todomvp.R
import com.homiak.andrii.todomvp.data.Task

class TasksFragment : Fragment(), TasksContract.View {

    private lateinit var tasksPresenter: TasksContract.Presenter

    private lateinit var tasksList: RecyclerView
    private val tasksAdapter by lazy { TasksAdapter() }
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.fragment_tasks, container, false)
        tasksList = view.findViewById(R.id.tasksList)
        tasksList.adapter = tasksAdapter
        tasksList.layoutManager = LinearLayoutManager(activity)
        return view
    }

    override fun setPresenter(presenter: TasksContract.Presenter) {
        tasksPresenter = presenter
    }

    override fun showTasksEmpty() {
        Toast.makeText(activity, "No tasks!", Toast.LENGTH_LONG).show()
    }

    override fun showTasks(tasks: List<Task>) {
        tasksAdapter.pushTasks(tasks)
        Toast.makeText(activity, "Loaded ${tasks.size} tasks!", Toast.LENGTH_LONG).show()
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

    class TasksAdapter : RecyclerView.Adapter<TaskViewHolder>()
    {
        private var tasks = mutableListOf<Task>()
        fun pushTasks(newTasks: List<Task>)
        {
            //add diffutil
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback(){
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return tasks[oldItemPosition].title == newTasks[newItemPosition].title
                }

                override fun getOldListSize() = tasks.size

                override fun getNewListSize() = newTasks.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return tasks[oldItemPosition].title == newTasks[newItemPosition].title
                            && tasks[oldItemPosition].description == newTasks[newItemPosition].description
                            && tasks[oldItemPosition].due == newTasks[newItemPosition].due
                }
            })
            tasks.clear()
            tasks.addAll(newTasks)
            result.dispatchUpdatesTo(this)
        }

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TaskViewHolder {
            val view = LayoutInflater.from(parent!!.context).inflate(R.layout.item_task, parent, false)
            return TaskViewHolder(view)
        }

        override fun getItemCount(): Int {
            return tasks.size
        }

        override fun onBindViewHolder(holder: TaskViewHolder?, position: Int) {
            with(tasks[position])
            {
                holder?.titleTV?.text = title
            }
        }
    }


    class TaskViewHolder(v : View) : RecyclerView.ViewHolder(v)
    {
        val titleTV : TextView by lazy { v.findViewById<TextView>(R.id.taskTitle) }
    }
}
