package com.mgarma.jadkuls2.ui.screens

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.mgarma.jadkuls2.R
import com.mgarma.jadkuls2.model.Course

@Composable
fun HomeScreen(
    coursesUiState: JadkulUiState,
    onRetryCourses: () -> Unit,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    Column(Modifier.fillMaxSize()) {
        Text(
            text = "Jadwal Kuliah",
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.CenterHorizontally),
            style = MaterialTheme.typography.headlineMedium
        )
        CourseList(
            coursesUiState,
            onRetryCourses,
            Modifier
        )
    }
}

@Composable
private fun CourseList(
    uiState: JadkulUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Log.d("CourseList", uiState.toString())
        when (uiState) {
            is JadkulUiState.Loading -> LoadingContent()
            is JadkulUiState.CoursesSuccess -> {
                Text(
                    "Mata Kuliah",
                    Modifier.padding(20.dp),
                    style = MaterialTheme.typography.headlineMedium
                )
                LazyColumn {
                    items(uiState.courses) { course ->
                        Log.d("CourseList", "Show items")
                        CourseItem(course)
                    }
                }
            }
            else -> ErrorContent(onRetry)
        }
    }
}

@Composable
private fun CourseItem(course: Course) {

    var isExpanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { isExpanded = !isExpanded },
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(text = course.attributes.name, style = MaterialTheme.typography.headlineSmall)

            // Animated Visibility
            AnimatedVisibility(
                visible = isExpanded,
                enter = fadeIn() + expandVertically(),
                exit = fadeOut() + shrinkVertically()
            ) {
                Column {
                    Text(text = "Kode Kelas: ${course.attributes.classCode}")
                    Text(text = "SKS: ${course.attributes.sks}")
                    Text(text = "Hari: ${course.attributes.day}")
                    Text(text = "Waktu: ${course.attributes.timeStart}")
                    Text(text = "Lokasi: ${course.attributes.Location}")
                }
            }
        }
    }
}

@Composable
private fun LoadingContent() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.loading_img),
            contentDescription = stringResource(id = R.string.loading)
        )
    }
}

@Composable
private fun ErrorContent(onRetry: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = stringResource(id = R.string.loading_failed))
            Button(onClick = onRetry) {
                Text(text = stringResource(id = R.string.retry))
            }
        }
    }
}
