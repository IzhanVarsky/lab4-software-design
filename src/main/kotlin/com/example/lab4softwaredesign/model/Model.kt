package com.example.lab4softwaredesign.model

class Task(name: String = "", description: String = "", var status: TaskStatus = TaskStatus.TODO) :
    NameableAndDescriptionable(name, description)

class ListOfTasks(name: String = "", description: String = "") :
    NameableAndDescriptionable(name, description)

open class NameableAndDescriptionable(name: String, description: String) {
    var name: String = name
        set(value) {
            field = value.trim()
        }
    var description: String = description
        set(value) {
            field = value.trim()
        }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as NameableAndDescriptionable

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

enum class TaskStatus {
    TODO,
    DONE;
}

