package com.example.artificialintelligencegame.game.level3

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Level3ViewModel : ViewModel() {
    private val _hideText = MutableLiveData<Unit>()
    val hideText: LiveData<Unit> = _hideText

    fun hideText() {
        _hideText.value = Unit
    }
}