package com.example.todo_approomjetpack.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.todo_approomjetpack.Todo

@Database(entities = [Todo::class], version = 1)
@TypeConverters(Converters::class)
abstract class TodoDatabase : RoomDatabase() {

    companion object {
        const val NAME = "TODO_DB"

    }

    abstract fun getTodoDao(): TodoDao


}