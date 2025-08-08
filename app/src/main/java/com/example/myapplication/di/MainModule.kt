package com.example.myapplication.di

import HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val loadViewModels = module {
    viewModel { HomeViewModel() }
}