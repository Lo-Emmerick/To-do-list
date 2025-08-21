package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.TaskDao
import com.example.myapplication.data.local.model.TaskEntity
import com.example.myapplication.data.model.Task
import com.example.myapplication.domain.repository.HomeRepository

class HomeRepositoryImpl(
    private val dao: TaskDao
) : HomeRepository {

    override suspend fun searchTask(): List<Task> = dao.getAllTasks()

    override suspend fun deleteTask(id: Int): List<Task> {
        dao.deleteTaskById(id)
        return searchTask()
    }

    override suspend fun addTask(task: TaskEntity): List<Task> {
        dao.insertTask(task)
        return searchTask()
    }

    override suspend fun editCheck(id: Int): List<Task> {
        dao.toggleTaskChecked(id)
        return searchTask()
    }
}