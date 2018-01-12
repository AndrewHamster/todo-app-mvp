package com.homiak.andrii.todomvp.util

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * Created by Andre on 13-Jan-18.
 */
class DateTypeConverter
{
    @TypeConverter
    fun toLong(date: Date): Long
            = date.time

    @TypeConverter
    fun toDate(long: Long): Date
            = Date(long)
}