package com.example.lab4softwaredesign.dao

import com.example.lab4softwaredesign.model.ListOfTasks
import com.example.lab4softwaredesign.model.Task

interface ItemDao {
    fun addList(list: ListOfTasks)
    fun getListsOfTasks(): Collection<ListOfTasks>
    fun getTasks(listName: String): Collection<Task>
    fun deleteList(listName: String)
    fun addTask(listName: String, newTask: Task)
    fun doneTask(listName: String, taskName: String)
    fun deleteTask(listName: String, taskName: String)
}