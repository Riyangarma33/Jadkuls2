package com.mgarma.jadkuls2.ui

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mgarma.jadkuls2.R
import com.mgarma.jadkuls2.ui.screens.JadkulViewModel
import com.mgarma.jadkuls2.ui.screens.HomeScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun JadwalKuliahApp() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        stringResource(R.string.app_name),
                        style = MaterialTheme.typography.headlineMedium,
                        color = MaterialTheme.colorScheme.background
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondary
                )
            )
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            val jadkulViewModel: JadkulViewModel =
                viewModel(factory = JadkulViewModel.Factory)
            Log.d("CollegeManagerApp", "Before HomeScreen ran")
            HomeScreen(
                coursesUiState = jadkulViewModel.coursesUiState.value,
                onRetryCourses = jadkulViewModel::getCourses,
                contentPadding = it
            )
        }
    }
}