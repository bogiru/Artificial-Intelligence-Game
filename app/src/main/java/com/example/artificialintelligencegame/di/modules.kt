package com.example.artificialintelligencegame.di

import com.example.artificialintelligencegame.game.level1.Level1ViewModel
import com.example.artificialintelligencegame.menu.MenuViewModel
import com.example.artificialintelligencegame.game.map.MapViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    viewModel { MapViewModel() }
    viewModel { MenuViewModel() }
    viewModel { Level1ViewModel() }
}