package com.example.myapplication.domain.model

import com.example.myapplication.data.model.Task

fun List<Task>.toTaskList(): TaskList {
    return TaskList(
        tasks = this,
        createdTask = this.count(),
        checkedTask = this.count {it.isChecked}
    )
}
