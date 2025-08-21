package com.example.myapplication.domain.usecase

import com.example.myapplication.data.local.model.TaskEntity
import com.example.myapplication.data.model.Task
import com.example.myapplication.domain.model.TaskList
import com.example.myapplication.domain.model.toTaskList
import com.example.myapplication.domain.repository.HomeRepository

class AddTaskUseCase(private val repository: HomeRepository) {
    suspend operator fun invoke(text: String): TaskList {
        val updatedTasks = repository.addTask(
            TaskEntity(
                id = 0,
                text = text,
                isChecked = false
            )
        )
        return updatedTasks.toTaskList()
    }
}