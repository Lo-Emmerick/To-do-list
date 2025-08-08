package com.example.myapplication.domain.usecase

import com.example.myapplication.data.Task
import com.example.myapplication.domain.repository.HomeRepository

class SearchTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): List<Task> {
        return repository.searchTask()
    }
}

class DeleteTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(id: Int): Task {
        return repository.deleteTask(id)
    }
}

class AddTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(text: String): Task {
        return repository.addTask(text)
    }
}