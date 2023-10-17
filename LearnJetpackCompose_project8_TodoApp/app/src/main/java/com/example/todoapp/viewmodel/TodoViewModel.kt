package com.example.todoapp.viewmodel

import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel

data class Todo (
    val id: Int,
    val name: String,
    val detail: String,
    val done: Boolean,
)

val todoListRaw: List<Todo> = listOf(
    Todo(0, "Shopping", "Mild, Eggs, Juice, ...", false),
    Todo(1, "Shopping", "Mild, Eggs, Juice, ...", true),
    Todo(2, "Shopping", "Mild, Eggs, Juice, ...", true),
    Todo(3, "Shopping", "Mild, Eggs, Juice, ...", false),
)

// View Model
class TodoViewModel: ViewModel() {
    private val _todoList = todoListRaw.toMutableStateList()
    val todoList: List<Todo>
        get() = _todoList

    fun switchStatus(todo: Todo) {
        val itemIndex = _todoList.indexOf(todo)
        _todoList.set(itemIndex, todo.copy(
            done = !todo.done,
        ))
    }
}
