package com.example.myapplication.domain.model

import com.example.myapplication.data.model.Task

data class TaskList(
    val tasks: List<Task>,
    val createdTask: Int,
    val checkedTask: Int
)