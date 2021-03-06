package com.homiak.andrii.todomvp.taskdetail


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView

import com.homiak.andrii.todomvp.R
import com.homiak.andrii.todomvp.data.Task
import kotlinx.android.synthetic.main.toolbar.view.*
import java.text.SimpleDateFormat
import java.util.*


/**
 * A simple [Fragment] subclass.
 * Use the [TaskDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskDetailFragment : Fragment(), TaskDetailContract.View {

    private var active = false

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        active = true
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        active = false
        super.onDestroyView()
    }

    override fun isActive() = isAdded

    override fun showTaskInfo(task: Task) {
        with(task)
        {
            titleTV.text = title
            dueDateTV.text = SimpleDateFormat("yyyy, MM, dd", Locale.US).format(dueDate)
            descTV.text = description
        }
    }

    private lateinit var taskDetailPresenter: TaskDetailContract.Presenter
    override fun setPresenter(presenter: TaskDetailContract.Presenter) {
        taskDetailPresenter = presenter
    }

    override fun showTaskDeleted() {
        activity.finish()
    }

    lateinit var titleTV: TextView
    lateinit var dueDateTV: TextView
    lateinit var descTV: TextView

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_task_detail, container, false)
        titleTV = view.findViewById(R.id.taskTitle)
        dueDateTV = view.findViewById(R.id.taskDueDate)
        descTV = view.findViewById(R.id.taskDescription)
        setHasOptionsMenu(true)
        (activity as? AppCompatActivity)?.apply {
            setSupportActionBar(view.toolbar)
            supportActionBar?.apply {
                title = getString(R.string.title_task_detail)
                setHomeButtonEnabled(true)
                setDisplayHomeAsUpEnabled(true)
            }
        }
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.task_detail, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.menu_delete_task -> taskDetailPresenter.deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        retainInstance = true
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context?) {
        taskDetailPresenter.start()
        super.onAttach(context)
    }

    companion object {
        fun newInstance(): TaskDetailFragment {
            return TaskDetailFragment()
        }
    }

}// Required empty public constructor
