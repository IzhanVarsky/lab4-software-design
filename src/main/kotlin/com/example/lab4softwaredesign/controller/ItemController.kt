package com.example.lab4softwaredesign.controller

import com.example.lab4softwaredesign.dao.ItemDao
import com.example.lab4softwaredesign.model.*
import org.springframework.stereotype.Controller
import org.springframework.ui.ModelMap
import org.springframework.web.bind.annotation.*

@Controller
class ItemController(private val itemDao: ItemDao) {
    @GetMapping("/")
    fun index(map: ModelMap): String {
        map.addAttribute("lists", itemDao.getListsOfTasks())
        map.addAttribute("newList", ListOfTasks())
        return "task-lists"
    }

    @GetMapping("/list/{listName}")
    fun getList(map: ModelMap, @PathVariable("listName") listName: String): String {
        val tasks = itemDao.getTasks(listName)
        map.addAttribute("listName", listName)
        map.addAttribute("tasks", tasks)
        map.addAttribute("newTask", Task())
        return "list-manager"
    }

    @PostMapping("/add-list")
    fun addList(@ModelAttribute("newList") newList: ListOfTasks): String {
        itemDao.addList(newList)
        return "redirect:/"
    }

    @PostMapping("/deleteList/{listName}")
    fun deleteList(@PathVariable("listName") listName: String): String {
        itemDao.deleteList(listName)
        return "redirect:/"
    }

    @PostMapping("/addTask/{listName}")
    fun addTask(
        @PathVariable("listName") listName: String,
        @ModelAttribute("newTask") newTask: Task
    ): String {
        itemDao.addTask(listName, newTask)
        return "redirect:/list/{listName}"
    }

    @PostMapping("/list/{listName}/done/{taskName}")
    fun doneTask(
        @PathVariable("listName") listName: String,
        @PathVariable("taskName") taskName: String
    ): String {
        itemDao.doneTask(listName, taskName)
        return "redirect:/list/{listName}"
    }

    @PostMapping("/list/{listName}/delete/{taskName}")
    fun deleteTask(
        @PathVariable("listName") listName: String,
        @PathVariable("taskName") taskName: String
    ): String {
        itemDao.deleteTask(listName, taskName)
        return "redirect:/list/{listName}"
    }
}