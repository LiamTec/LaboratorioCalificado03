package com.gonzales.liam.laboratoriocalificado03

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.gonzales.liam.laboratoriocalificado03.TeacherAdapter
import com.gonzales.liam.laboratoriocalificado03.databinding.ActivityMainBinding
import com.gonzales.liam.laboratoriocalificado03.TeacherApi
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TeacherAdapter
    private lateinit var api: TeacherApi

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = Retrofit.Builder()
            .baseUrl("https://private-effe28-tecsup1.apiary-mock.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        api = retrofit.create(TeacherApi::class.java)

        adapter = TeacherAdapter(this, emptyList())
        binding.recyclerViewTeachers.layoutManager = LinearLayoutManager(this)
        binding.recyclerViewTeachers.adapter = adapter

        binding.progressBar.visibility = View.VISIBLE

        lifecycleScope.launch {
            try {
                val response = api.getTeachers()
                adapter.submitList(response.teachers)
                binding.progressBar.visibility = View.GONE
                binding.recyclerViewTeachers.visibility = View.VISIBLE
            } catch (e: Exception) {
                binding.progressBar.visibility = View.GONE
                Toast.makeText(this@MainActivity, getString(R.string.error_fetch), Toast.LENGTH_LONG).show()
            }
        }
    }
}