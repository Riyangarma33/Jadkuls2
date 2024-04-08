package com.mgarma.jadkuls2.network

import com.mgarma.jadkuls2.model.CourseResponse
import retrofit2.http.GET

interface CollegeApiService {
    @GET("jadkuls")
    suspend fun getCourses(): CourseResponse
}