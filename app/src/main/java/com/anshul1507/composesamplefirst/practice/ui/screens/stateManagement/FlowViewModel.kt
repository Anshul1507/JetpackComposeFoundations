package com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FlowViewModel: ViewModel() {

    private val _timerState = MutableStateFlow(0)
    val timerState: StateFlow<Int> = _timerState.asStateFlow()

    init {
        startTimerLoop()
    }

    private fun startTimerLoop() {
        viewModelScope.launch {
            while (true) {
                delay(1000)
                _timerState.value += 1
            }
        }
    }

    fun resetTimer() {
        _timerState.value = 0
    }
}