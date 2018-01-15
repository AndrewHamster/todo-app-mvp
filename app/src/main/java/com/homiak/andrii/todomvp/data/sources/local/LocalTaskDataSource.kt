package com.homiak.andrii.todomvp.data.sources.local

import android.annotation.SuppressLint
import android.os.AsyncTask
import com.homiak.andrii.todomvp.data.Task
import com.homiak.andrii.todomvp.data.TaskDataSource

/**
 * Created by Andre on 12-Jan-18.
 */
@SuppressLint("StaticFieldLeak")
class LocalTaskDataSource private constructor(private val taskDao: TaskDao): TaskDataSource
{
    override fun saveTask(task: Task, callback: TaskDataSource.SaveTaskCallback) {
        object : AsyncParameterlessTask()
        {
            override fun doInBackground(vararg p0: Unit?) {
                taskDao.saveTask(task)
            }

            override fun onPostExecute(result: Unit?) {
                callback.onTaskSaved()
            }
        }.execute()
    }

    companion object {
        private var INSTANCE: LocalTaskDataSource? = null
        fun getInstance(taskDao: TaskDao): LocalTaskDataSource
        {
            if(INSTANCE == null)
            {
                INSTANCE = LocalTaskDataSource(taskDao)
            }
            return INSTANCE!!
        }
    }

    //todo check out executors
    override fun getTasks(callback: TaskDataSource.LoadTasksCallback) {
        object : AsyncParameterlessTask()
        {
            private lateinit var tasks: List<Task>
            override fun doInBackground(vararg p0: Unit?) {
                tasks = taskDao.getAllTasks()
            }

            override fun onPostExecute(result: Unit?) {
                callback.onTasksLoaded(tasks)
            }
        }.execute()
    }


    //todo check out executors
    override fun getTask(id: Int, callback: TaskDataSource.LoadTaskCallback) {
        object : AsyncParameterlessTask()
        {
            private var task: Task? = null
            override fun doInBackground(vararg p0: Unit?) {
                task = taskDao.getTaskById(id)
            }

            override fun onPostExecute(result: Unit?) {
                if(task != null)
                    callback.onTaskLoaded(task!!)
                else
                    callback.onTaskNoFound()
            }
        }.execute()
    }

    private abstract class AsyncParameterlessTask : AsyncTask<Unit, Unit, Unit>()
}