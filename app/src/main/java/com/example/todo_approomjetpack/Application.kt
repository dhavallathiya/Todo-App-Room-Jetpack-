package com.example.todo_approomjetpack

import android.app.Application
import androidx.room.Room
import com.example.todo_approomjetpack.db.TodoDatabase

class Application : Application() {

    companion object {
        lateinit var todoDatabase: TodoDatabase
    }

    override fun onCreate() {
        super.onCreate()

        todoDatabase =
            Room.databaseBuilder(applicationContext, TodoDatabase::class.java, TodoDatabase.NAME)
                .build()
    }
}