package com.mgarma.jadkuls2.ui.screens

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mgarma.jadkuls2.JadwalKuliahApplication
import com.mgarma.jadkuls2.data.JadkulRepository
import com.mgarma.jadkuls2.model.Course
import kotlinx.coroutines.launch

sealed interface JadkulUiState {
    data class CoursesSuccess(val courses: List<Course>) : JadkulUiState
    object Error : JadkulUiState
    object Loading : JadkulUiState
}

class JadkulViewModel(private val jadkulRepository: JadkulRepository) : ViewModel() {

    var jadkulUiState: JadkulUiState by mutableStateOf(JadkulUiState.Loading)
        private set

    private val _coursesUiState = mutableStateOf<JadkulUiState>(JadkulUiState.Loading)
    val coursesUiState: State<JadkulUiState> = _coursesUiState

    init {
        Log.d("JadkulViewModel", "Init ran")
        getCourses()
    }

    fun getCourses() {
        viewModelScope.launch {
            _coursesUiState.value = JadkulUiState.Loading
            try {
                val courses = jadkulRepository.getCourses()
                _coursesUiState.value = JadkulUiState.CoursesSuccess(courses)
            } catch (e: Exception) {
                Log.d("getCourses", "Error: $e")
                _coursesUiState.value = JadkulUiState.Error
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                        as JadwalKuliahApplication)
                val collegeRepository = application.container.jadkulRepository
                JadkulViewModel(collegeRepository)
            }
        }
    }
}