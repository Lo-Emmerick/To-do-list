package com.example.myapplication.domain.usecase

import com.example.myapplication.data.Task
import com.example.myapplication.domain.repository.HomeRepository

class SearchTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(): List<Task> {
        return repository.searchTask()
    }
}