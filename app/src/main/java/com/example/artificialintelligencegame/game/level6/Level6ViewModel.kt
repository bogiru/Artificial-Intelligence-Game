package com.example.artificialintelligencegame.game.level6

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

const val FINISG_SCORE = 10

class Level6ViewModel : ViewModel() {

    private val _robotScore = MutableLiveData<Int>( 0)
    val robotScore: LiveData<Int> = _robotScore

    private val _userScore = MutableLiveData<Int>( 0)
    val userScore: LiveData<Int> = _userScore

    private val _winner = MutableLiveData<String>()
    val winner: LiveData<String> = _winner

    private val _isVisibleRobotLayout = MutableLiveData<Boolean>(true)
    val isVisibleRobotLayout: LiveData<Boolean> = _isVisibleRobotLayout

    private val _showNewRobotText = MutableLiveData(1)
    val showNewRobotText: LiveData<Int> = _showNewRobotText

    private val _objectInFrame = MutableLiveData<Boolean>( false)
    val objectInFrame: LiveData<Boolean> = _objectInFrame

    val objectIsEdible = MutableLiveData(false)

    fun showNewRobotText() {
        _showNewRobotText.value = _showNewRobotText.value!! + 1
    }

    fun hideRobotLayout() {
        _isVisibleRobotLayout.value = false
    }

    fun showRobotLayout() {
        _isVisibleRobotLayout.value = true
    }


    fun placeObjectInFrame() {
        objectIsEdible.value = false
        _objectInFrame.value = true
    }

    private fun removeObjectFromFrame() {
        _objectInFrame.value = false
    }

    fun increaseUserScore() {
        _userScore.value = _userScore.value?.plus(1)
        removeObjectFromFrame()
        checkFinishGame()
    }

    fun increaseRobotScore() {
        _robotScore.value = _robotScore.value?.plus(1)
        removeObjectFromFrame()
        checkFinishGame()
    }
    
    private fun checkFinishGame() {
        if (robotScore.value == FINISG_SCORE) {
            _winner.value = "user"
            showRobotLayout()
        }

        if (userScore.value == FINISG_SCORE) {
            _winner.value = "robot"
            showRobotLayout()
        }

    }

}