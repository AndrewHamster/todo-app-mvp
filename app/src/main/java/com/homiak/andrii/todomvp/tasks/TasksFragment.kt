package com.homiak.andrii.todomvp.tasks

import android.content.Intent
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
import com.homiak.andrii.todomvp.taskdetail.TaskDetailActivity

class TasksFragment : Fragment(), TasksContract.View {

    private lateinit var tasksPresenter: TasksContract.Presenter
    private lateinit var tasksList: RecyclerView
    private val tasksAdapter by lazy {
        TasksAdapter(object : TaskActionListener {
            override fun onTaskClick(task: Task) {
                val intent = Intent(activity, TaskDetailActivity::class.java)
                intent.putExtra(TaskDetailActivity.TASK_ID, task.id)
                activity.startActivity(intent)
            }
        })
    }

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

    class TasksAdapter(val listener: TaskActionListener) : RecyclerView.Adapter<TaskViewHolder>() {
        private var tasks = mutableListOf<Task>()
        fun pushTasks(newTasks: List<Task>) {
            //add diffutil
            val result = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return tasks[oldItemPosition].title == newTasks[newItemPosition].title
                }

                override fun getOldListSize() = tasks.size

                override fun getNewListSize() = newTasks.size

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                    return tasks[oldItemPosition].title == newTasks[newItemPosition].title
                            && tasks[oldItemPosition].description == newTasks[newItemPosition].description
                            && tasks[oldItemPosition].dueDate == newTasks[newItemPosition].dueDate
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
            val task = tasks[position]
            holder?.apply {
                view.setOnClickListener { listener.onTaskClick(task) }
                titleTV.text = task.title
            }
        }
    }


    class TaskViewHolder(val view: View) : RecyclerView.ViewHolder(view)
    {
        val titleTV: TextView = view.findViewById(R.id.taskTitle)
    }

    interface TaskActionListener {
        fun onTaskClick(task: Task)
    }
}
