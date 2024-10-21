package com.example.finale.ui

import com.example.finale.data.LoginResponse
import com.example.finale.data.ApiRepository
import com.example.finale.data.LoginRequest
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val repository : ApiRepository) : ViewModel()  {

    private val mutableObjectsState = MutableStateFlow(LoginResponse(keypass = ""))
    val objectsState: StateFlow<LoginResponse> = mutableObjectsState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun getkeypass() {
        viewModelScope.launch {
            try {
                val entities = repository.getkeypass(LoginRequest("s8093929", "Lachlan"))
                mutableObjectsState.value = entities
            } catch (e: Exception) {
                _errorState.value = "Error fetching objects: ${e.message}"
            }
        }
    }
}