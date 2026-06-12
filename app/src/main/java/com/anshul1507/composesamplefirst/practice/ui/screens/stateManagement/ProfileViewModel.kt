package com.anshul1507.composesamplefirst.practice.ui.screens.stateManagement

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {

    private val _username = MutableLiveData("Initial Dev State")
    val username: LiveData<String> = _username

    private val _isVerificationPending = MutableLiveData(false)
    val isVerificationPending: LiveData<Boolean> = _isVerificationPending

    fun updateUserHandle(newName: String) {
        if (newName.isNotBlank()) {
            _username.value = newName
        }
    }

    fun toggleVerificationStatus() {
        _isVerificationPending.value = !(_isVerificationPending.value ?: false)
    }
}