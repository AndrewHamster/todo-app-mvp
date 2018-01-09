package com.homiak.andrii.todomvp.tasks

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.homiak.andrii.todomvp.R
import com.homiak.andrii.todomvp.data.TaskRepository

class TasksActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tasks)
        val view = TasksFragment.newInstance()
//        setSupportActionBar(toolbar)
//        supportActionBar?.apply {
//            setHomeButtonEnabled(true)
//            setDisplayHomeAsUpEnabled(true)
//            setTitle(R.string.title_task_detail)
//        }

        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, view)
                .commit()
        val presenter = TasksPresenter(TaskRepository.instance, view)
        view.setPresenter(presenter)
    }
}
