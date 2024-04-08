package com.mgarma.jadkuls2.data

import com.mgarma.jadkuls2.model.Course
import com.mgarma.jadkuls2.network.CollegeApiService

interface JadkulRepository {
    suspend fun getCourses(): List<Course>
}

class DefaultJadkulRepository (
    private val apiService: CollegeApiService
) : JadkulRepository {
    override suspend fun getCourses(): List<Course> = apiService.getCourses().data
}