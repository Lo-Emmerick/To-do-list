package com.example.myapplication.domain.usecase

import com.example.myapplication.data.Task
import com.example.myapplication.domain.repository.HomeRepository

class AddTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(text: String): List<Task> {
        return repository.addTask(text)
    }
}
