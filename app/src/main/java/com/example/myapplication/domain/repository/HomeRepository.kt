package com.example.myapplication.domain.repository

import com.example.myapplication.data.local.model.TaskEntity
import com.example.myapplication.data.model.Task

interface HomeRepository {
    suspend fun searchTask() : List<Task>
    suspend fun deleteTask(task: Task) : List<Task>
    suspend fun addTask(task: TaskEntity) :  List<Task>
    suspend fun editCheck(task: Task) :  List<Task>
}