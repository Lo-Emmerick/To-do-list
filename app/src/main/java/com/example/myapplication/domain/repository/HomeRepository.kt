package com.example.myapplication.domain.repository

import com.example.myapplication.data.Task

interface HomeRepository {
    suspend fun searchTask() : List<Task>
    suspend fun deleteTask(id: Int) : Task
    suspend fun addTask(text: String) : Task
}