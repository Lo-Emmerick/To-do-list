package com.example.myapplication.presentation.ui.home

import com.example.myapplication.data.Task

sealed interface HomeState {
    data class Success(val taskList: List<Task>) : HomeState
    object Loading : HomeState
    object Empty : HomeState
    object Error : HomeState
}