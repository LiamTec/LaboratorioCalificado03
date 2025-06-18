package com.gonzales.liam.laboratoriocalificado03

import com.gonzales.liam.laboratoriocalificado03.TeachersListResponse
import retrofit2.http.GET

interface TeacherApi {
    @GET("list/teacher")
    suspend fun getTeachers(): TeachersListResponse
}