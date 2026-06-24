package com.anshul1507.composesamplefirst.practice.ui.screens.architecture

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface TelemetryUIState {
    data object Loading: TelemetryUIState
    data class Success(val metricData: String): TelemetryUIState
}

@HiltViewModel
class TelemetryViewModel @Inject constructor(): ViewModel() {
    private val _uiState = MutableStateFlow<TelemetryUIState>(TelemetryUIState.Loading)
    val uiState: StateFlow<TelemetryUIState> = _uiState.asStateFlow()

    init {
        loadSystemMetrics()
    }

    fun loadSystemMetrics() {
        viewModelScope.launch {
            _uiState.value = TelemetryUIState.Loading
            delay(1500)
            _uiState.value = TelemetryUIState.Success("All node core pipelines: STABLE [2026-OK]")
        }
    }
}