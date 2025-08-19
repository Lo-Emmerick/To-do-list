package com.example.myapplication.presentation.ui.home.adapter

import com.example.myapplication.data.model.Task

interface HomeListener {
    fun deleteTask(item:Task)
    fun checked(item:Task)
}