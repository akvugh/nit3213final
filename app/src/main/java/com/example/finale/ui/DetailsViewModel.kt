package com.example.finale.ui

import com.example.finale.data.ApiRepository
import com.example.finale.data.Entity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val repository : ApiRepository) : ViewModel()  {

    private val _entitiesState = MutableStateFlow<List<Entity>>(emptyList())
    val entitiesState: StateFlow<List<Entity>> = _entitiesState

    // StateFlow to handle the entity total
    private val _entityTotalState = MutableStateFlow(0)
    val entityTotalState: StateFlow<Int> = _entityTotalState

    private val _errorState = MutableStateFlow<String?>(null)
    val errorState: StateFlow<String?> = _errorState

    fun getAllObjects() {
        viewModelScope.launch {
            try {
                val response = repository.getAllEntities()
                _entitiesState.value = response.entities
                _entityTotalState.value = response.entityTotal
            } catch (e: Exception) {
                _errorState.value = "Error fetching objects: ${e.message}"
            }
        }
    }
}