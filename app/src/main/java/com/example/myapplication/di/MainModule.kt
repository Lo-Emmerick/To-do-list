package com.example.myapplication.di

import HomeViewModel
import com.example.myapplication.data.repository.HomeRepositoryImpl
import com.example.myapplication.domain.repository.HomeRepository
import com.example.myapplication.domain.usecase.AddTaskUseCase
import com.example.myapplication.domain.usecase.DeleteTaskUseCase
import com.example.myapplication.domain.usecase.EditCheckUseCase
import com.example.myapplication.domain.usecase.SearchTaskUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadRepositorie = module {
    single { HomeRepositoryImpl() as HomeRepository }
}
val loadUseCase = module {
    single { SearchTaskUseCase(repository = get()) }
    single { DeleteTaskUseCase(repository = get()) }
    single { AddTaskUseCase(repository = get()) }
    single { EditCheckUseCase(repository = get()) }
}
val loadViewModel = module {
    viewModel {
        HomeViewModel(
            editCheckUseCase = get(),
            addTaskUseCase = get(),
            deleteTaskUseCase = get(),
            searchTaskUseCase = get()
        )
    }
}