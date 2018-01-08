package com.homiak.andrii.todomvp.taskdetail


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*

import com.homiak.andrii.todomvp.R
import com.homiak.andrii.todomvp.data.Task


/**
 * A simple [Fragment] subclass.
 * Use the [TaskDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TaskDetailFragment : Fragment(), TaskDetailContract.View {
    override fun showTaskInfo(task: Task) {

    }

    private lateinit var taskDetailPresenter: TaskDetailContract.Presenter
    override fun setPresenter(presenter: TaskDetailContract.Presenter) {
        taskDetailPresenter = presenter
    }

    override fun showTaskDeleted() {
        activity.finish()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater!!.inflate(R.layout.fragment_task_detail, container, false)
        setHasOptionsMenu(true)
        return view
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        inflater?.inflate(R.menu.task_detail_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId)
        {
            R.id.menu_delete_task -> taskDetailPresenter.deleteTask()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        taskDetailPresenter.start()
        super.onResume()
    }

    companion object {
        fun newInstance(): TaskDetailFragment {
            return TaskDetailFragment()
        }
    }

}// Required empty public constructor
