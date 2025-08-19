package com.example.myapplication.domain.usecase

import com.example.myapplication.data.model.Task
import com.example.myapplication.domain.model.TaskList
import com.example.myapplication.domain.model.toTaskList
import com.example.myapplication.domain.repository.HomeRepository

class DeleteTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(item: Task): TaskList {
        val updatedTasks = repository.deleteTask(item)
        return updatedTasks.toTaskList()
    }
}