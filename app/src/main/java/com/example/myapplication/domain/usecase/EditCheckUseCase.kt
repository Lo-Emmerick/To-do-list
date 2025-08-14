package com.example.myapplication.domain.usecase

import com.example.myapplication.data.Task
import com.example.myapplication.domain.repository.HomeRepository

class EditCheckUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(information: Task): List<Task> {
        return repository.editCheck(information)
    }
}