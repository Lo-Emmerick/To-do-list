package com.example.myapplication.data.repository

import com.example.myapplication.data.Task
import com.example.myapplication.domain.repository.HomeRepository

class HomeRepositoryImpl : HomeRepository {

    override suspend fun searchTask(): List<Task> {
        return listOf(
            Task(id = 1, isChecked = true, text = "limpar a casa"),
            Task(id = 2, isChecked = false, text = "lavar roupa")
        )
    }

    override suspend fun deleteTask(id: Int): Task {
        return Task(
            id = id,
            isChecked = false,
            text = "limpar a casa"
        )
    }

    override suspend fun addTask(text: String): Task {
        return Task(
            id = 2,
            isChecked = true,
            text = text
        )
    }
}