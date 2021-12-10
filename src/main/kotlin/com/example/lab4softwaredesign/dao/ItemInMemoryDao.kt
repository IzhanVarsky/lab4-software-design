package com.example.lab4softwaredesign.dao

import com.example.lab4softwaredesign.model.ListOfTasks
import com.example.lab4softwaredesign.model.Task
import com.example.lab4softwaredesign.model.TaskStatus

class ItemInMemoryDao : ItemDao {
    private val lists: MutableMap<ListOfTasks, MutableList<Task>> = mutableMapOf()

    init {
        val list1 = ListOfTasks("do Kotlin", "a lot of things!!")
        val list2 = ListOfTasks("do PPO", "OMG...")
        lists[list1] = mutableListOf(
            Task("taskName1", "desc1", TaskStatus.DONE),
            Task("taskName2", "desc2", TaskStatus.TODO)
        )
        addList(list2)
    }

    override fun addList(list: ListOfTasks) {
        if (list.name.isEmpty()) return
        lists[list] = mutableListOf()
    }

    override fun getListsOfTasks(): Collection<ListOfTasks> = lists.keys

    override fun getTasks(listName: String): Collection<Task> =
        lists.getOrDefault(ListOfTasks(listName), listOf())

    override fun deleteList(listName: String) {
        lists.remove(ListOfTasks(listName))
    }

    override fun addTask(listName: String, newTask: Task) {
        if (newTask.name.isEmpty()) return
        getTasksMutable(listName)?.add(newTask)
    }

    override fun doneTask(listName: String, taskName: String) {
        val list = getTasksMutable(listName) ?: return
        list[findTaskIndByName(list, taskName)].status = TaskStatus.DONE
    }

    override fun deleteTask(listName: String, taskName: String) {
        val list = getTasksMutable(listName) ?: return
        list.removeAt(findTaskIndByName(list, taskName))
    }

    private fun findTaskIndByName(list: List<Task>, taskName: String): Int =
        list.indexOfFirst { it.name == taskName }

    private fun getTasksMutable(listName: String): MutableList<Task>? =
        lists[ListOfTasks(listName)]
}
