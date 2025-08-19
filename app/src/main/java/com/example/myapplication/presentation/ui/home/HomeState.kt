package com.example.myapplication.presentation.ui.home

import com.example.myapplication.domain.model.TaskList

sealed interface HomeState {
    data class Success(val taskList: TaskList) : HomeState
    object Loading : HomeState
    object Empty : HomeState
    object Error : HomeState
}