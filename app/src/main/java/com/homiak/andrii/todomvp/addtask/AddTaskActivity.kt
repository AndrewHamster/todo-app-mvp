package com.homiak.andrii.todomvp.addtask

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.homiak.andrii.todomvp.Injection
import com.homiak.andrii.todomvp.R

class AddTaskActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_task)
        val view = AddTaskFragment.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.fragment_container, view)
                .commit()
        val presenter = AddTaskPresenter(Injection.provideTaskRepository(this), view)
        view.setPresenter(presenter)
    }
}
