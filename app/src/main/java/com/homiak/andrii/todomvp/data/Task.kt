package com.homiak.andrii.todomvp.data

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.arch.persistence.room.TypeConverters
import com.homiak.andrii.todomvp.util.DateTypeConverter
import java.util.*

/**
 * Created by Andre on 06-Jan-18.
 */

@Entity(tableName = "tasks")
@TypeConverters(DateTypeConverter::class)
data class Task(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "_id", index = true)
        var id: Int = 0,

        @ColumnInfo(name = "title")
        val title: String,

        @ColumnInfo(name = "description")
        val description: String,

        @ColumnInfo(name = "due_date")
        val dueDate: Date)

