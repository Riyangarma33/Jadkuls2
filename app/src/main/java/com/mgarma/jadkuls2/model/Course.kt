package com.mgarma.jadkuls2.model

import kotlinx.serialization.Serializable

@Serializable
data class CourseResponse(
    val data: List<Course>,
//    val meta: Meta
)

@Serializable
data class Course(
    val id: Int,
    val attributes: CourseAttributes
)

@Serializable
data class CourseAttributes(
    val name: String,
    val classCode: String,
    val sks: Int,
    val day: String,
    val timeStart: String,
//    val timeEnd: String,
//    val classLocation: String,
    val Location: String,
    val createdAt: String,
    val updatedAt: String,
    val publishedAt: String
)

@Serializable
data class Meta(
    val pagination: Pagination
)

@Serializable
data class Pagination(
    val page: Int,
    val pageSize: Int,
    val pageCount: Int,
    val total: Int
)


//@Serializable
//enum class Day {
//    Monday,
//    Tuesday,
//    Wednesday,
//    Thursday,
//    Friday,
//    Saturday,
//    Sunday
//}

//data class Course(
//    val id: String,
//    val name: String,
//    val classCode: String,
//    val sks: Int,
//    val day: Day,
//    val timeStart: String,
//    val timeEnd: String,
//    val classLocation: String
//)
