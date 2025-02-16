package com.example.todo_approomjetpack.db

import androidx.room.TypeConverter
import java.util.Date

class Converters {

    @TypeConverter
    fun FromData(date: Date): Long {
        return date.time
    }

    @TypeConverter
    fun toData(time: Long): Date {
        return Date(time)
    }
}