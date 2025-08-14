package com.example.myapplication.domain.usecase

import com.example.myapplication.data.Task
import com.example.myapplication.domain.repository.HomeRepository


class DeleteTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(item: Task): List<Task> {
        return repository.deleteTask(item)
    }
}