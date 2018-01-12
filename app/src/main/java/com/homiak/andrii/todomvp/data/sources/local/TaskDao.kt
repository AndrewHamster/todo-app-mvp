package com.homiak.andrii.todomvp.data.sources.local

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.homiak.andrii.todomvp.data.Task

/**
 * Created by Andre on 12-Jan-18.
 */
@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAllTasks(): List<Task>

    @Query("SELECT * FROM tasks WHERE _id = :id LIMIT 1")
    fun getTaskById(id: Int): Task?
}