package com.gonzales.liam.laboratoriocalificado03

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gonzales.liam.laboratoriocalificado03.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private var listTeachers: List<TeacherResponse> = emptyList()
    private val adapter by lazy { TeacherAdapter(listTeachers) }
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.rvTeachers.adapter = adapter
        getAllTeachers()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun getAllTeachers() {
        CoroutineScope(Dispatchers.IO).launch {
            val request = getRetrofit().create(TeacherApi::class.java).getTeachers()
            if (request.isSuccessful) {
                request.body()?.let { teachers ->
                    runOnUiThread {
                        adapter.list = teachers
                        adapter.notifyDataSetChanged()
                    }
                }
            }
        }
    }
}