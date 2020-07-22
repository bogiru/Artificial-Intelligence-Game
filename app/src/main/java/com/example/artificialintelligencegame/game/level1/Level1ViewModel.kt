package com.example.artificialintelligencegame.game.level1

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Level1ViewModel : ViewModel() {
    private val _showNewRobotText = MutableLiveData(1)
    val showNewRobotText: LiveData<Int> = _showNewRobotText

    fun showNewRobotText() {
        _showNewRobotText.value = _showNewRobotText.value!! + 1
    }

}