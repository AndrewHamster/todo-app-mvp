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
        supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, view)
                .commit()
        val presenter = TaskPresenter(TaskRepository(), view)
        view.setPresenter(presenter)
    }
}
