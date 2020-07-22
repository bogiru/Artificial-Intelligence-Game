package com.example.artificialintelligencegame.game.map

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MapViewModel : ViewModel() {
    private val _openLevelFragment = MutableLiveData<Int>()
    val openLevelFragment: LiveData<Int> = _openLevelFragment

    fun openLevel(index: Int) {
        _openLevelFragment.value = index
    }

}