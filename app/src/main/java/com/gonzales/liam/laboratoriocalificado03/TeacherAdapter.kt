package com.gonzales.liam.laboratoriocalificado03

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gonzales.liam.laboratoriocalificado03.databinding.ItemTeacherBinding

class TeacherAdapter(
    var list: List<TeacherResponse>
): RecyclerView.Adapter<TeacherAdapter.ViewHolder>() {

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = ItemTeacherBinding.bind(view)

        fun bind(teacher: TeacherResponse) {
            binding.tvName.text = "${teacher.name} ${teacher.last_name}"
            binding.tvEmail.text = teacher.email
            Glide
                .with(itemView)
                .load(teacher.imageUrl)
                .into(binding.ivTeacher)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val teacher = list[position]
        holder.bind(teacher)
    }

    override fun getItemCount(): Int = list.size
}