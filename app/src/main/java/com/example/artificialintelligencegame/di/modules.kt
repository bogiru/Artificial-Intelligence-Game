package com.example.artificialintelligencegame.di

import com.example.artificialintelligencegame.study.map.MapViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    viewModel { MapViewModel() }
}