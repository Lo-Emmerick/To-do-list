package com.example.myapplication.di

import HomeViewModel
import com.example.myapplication.data.repository.HomeRepositoryImpl
import com.example.myapplication.domain.repository.HomeRepository
import com.example.myapplication.domain.usecase.AddTaskUseCase
import com.example.myapplication.domain.usecase.DeleteTaskUseCase
import com.example.myapplication.domain.usecase.SearchTaskUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadRepositorie = module {
    single { HomeRepositoryImpl() as HomeRepository }
}
val loadUseCase = module {
    single { SearchTaskUseCase(get()) }
    single { DeleteTaskUseCase(get()) }
    single { AddTaskUseCase(get()) }
}
val loadViewModel = module {
    viewModel {
        HomeViewModel(
            get(),
            get(),
            get()
        )
    }
}