package com.example.myapplication.data.repository

import com.example.myapplication.data.model.Task
import com.example.myapplication.domain.repository.HomeRepository

class HomeRepositoryImpl : HomeRepository {

    var taskList = listOf<Task>()

    override suspend fun searchTask(): List<Task> {
        return taskList
    }

    override suspend fun deleteTask(information: Task): List<Task> {
        taskList = taskList.filter{ it.text != information.text }
        return taskList
    }

    override suspend fun addTask(text: String): List<Task> {
        taskList+= listOf(Task(id = taskList.size, false, text))
        return taskList
    }

    override suspend fun editCheck(information: Task): List<Task> {
        taskList = taskList.filter { it.text != information.text }
        taskList+= listOf(information.copy(isChecked = !information.isChecked))
        return taskList
    }
}