package com.example.myapplication.domain.usecase

import com.example.myapplication.domain.model.TaskList
import com.example.myapplication.domain.model.toTaskList
import com.example.myapplication.domain.repository.HomeRepository

class SearchTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): TaskList {
        val updatedTasks = repository.searchTask()
        return updatedTasks.toTaskList()
    }
}