package com.homiak.andrii.todomvp.taskdetail

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.homiak.andrii.todomvp.R
import com.homiak.andrii.todomvp.data.TaskRepository

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var presenter: TaskDetailPresenter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_detail)
        val fragment = TaskDetailFragment.newInstance()
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit()
        if(intent.hasExtra(TASK_ID))
        {
            presenter = TaskDetailPresenter(fragment, TaskRepository.instance, intent.getIntExtra(TASK_ID, 0))
            fragment.setPresenter(presenter)
        }
    }

    companion object {
        const val TASK_ID = "TASK_ID"
    }
}
