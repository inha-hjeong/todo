package com.example.mpproject.data

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class TaskModel : ViewModel() {
    private var _todoList = mutableStateListOf<Task>()

    fun getAllToDoList(): List<Task> {
        return _todoList;
    }

    fun addTodoList(todoItem: Task) {
        _todoList.add(todoItem)
    }

    fun removeTodoItem(todoItem: Task) {
        _todoList.remove(todoItem)
    }
}