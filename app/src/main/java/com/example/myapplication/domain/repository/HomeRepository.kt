package com.example.myapplication.domain.repository

import com.example.myapplication.data.Task

interface HomeRepository {
    suspend fun searchTask() : List<Task>
    suspend fun deleteTask(information: Task) : List<Task>
    suspend fun addTask(text: String) :  List<Task>
    suspend fun editCheck(information: Task) :  List<Task>
}