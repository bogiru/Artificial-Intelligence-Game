package com.example.artificialintelligencegame.di

import com.example.artificialintelligencegame.game.level1.Level1ViewModel
import com.example.artificialintelligencegame.game.level2.Level2ViewModel
import com.example.artificialintelligencegame.game.level3.Level3ViewModel
import com.example.artificialintelligencegame.game.level6.Level6ViewModel
import com.example.artificialintelligencegame.menu.MenuViewModel
import com.example.artificialintelligencegame.game.map.MapViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val modules = module {

    viewModel { MapViewModel() }
    viewModel { MenuViewModel() }
    viewModel { Level1ViewModel() }
    viewModel { Level2ViewModel() }
    viewModel { Level3ViewModel() }
    viewModel { Level6ViewModel() }
}