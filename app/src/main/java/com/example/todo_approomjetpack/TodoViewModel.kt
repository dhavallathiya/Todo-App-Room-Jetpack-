package com.example.todo_approomjetpack

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TodoViewModel : ViewModel()  {
    private val _todoList = MutableLiveData<List<Todo>>()
    val todosList: LiveData<List<Todo>> = _todoList


    fun getAllTodo() {
        _todoList.value = TodoManager.getAllTodo().reversed()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title: String)  {
        TodoManager.addTodo(title)
        getAllTodo()
    }

    fun deleteTodo(id: Int)  {
        TodoManager.deleteTodo(id)
        getAllTodo()
    }
}