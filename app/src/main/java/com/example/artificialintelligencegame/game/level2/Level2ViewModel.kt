package com.example.artificialintelligencegame.game.level2

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class Level2ViewModel : ViewModel() {

    private val _currentRobot = MutableLiveData<View>()
    val showRobot: LiveData<View> = _currentRobot

    private val _hideText = MutableLiveData<Unit>()
    val hideText: LiveData<Unit> = _hideText

    val foundRobots = mutableListOf<Int>()

    private val _openNextLevel = MutableLiveData<Unit>()
    val openNextLevel: LiveData<Unit> = _openNextLevel

    fun showRobot(view: View) {
        addRobotToListFoundRobots(view.id)
        _currentRobot.value = view
    }

    fun hideText() {
        if (foundRobots.size == 9) {
            openNextLevel()
        }
        _hideText.value = Unit
    }

    private fun addRobotToListFoundRobots(robotId: Int) {
        if (!foundRobots.contains(robotId)) {
            foundRobots.add(robotId)
        }
    }

    private fun openNextLevel() {
        _openNextLevel.value = Unit
    }
}