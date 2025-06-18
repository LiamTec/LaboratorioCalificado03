package com.gonzales.liam.laboratoriocalificado03

import retrofit2.Response
import retrofit2.http.GET

interface TeacherApi {
    @GET("/list/teacher")
    fun getTeachers(): Response<List<TeacherResponse>>
}