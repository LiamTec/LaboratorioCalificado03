package com.gonzales.liam.laboratoriocalificado03

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gonzales.liam.laboratoriocalificado03.databinding.ItemTeacherBinding
import com.gonzales.liam.laboratoriocalificado03.TeacherResponse

class TeacherAdapter(
    private val context: Context,
    private var teachers: List<TeacherResponse>
) : RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    fun submitList(data: List<TeacherResponse>) {
        teachers = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }

    override fun getItemCount(): Int = teachers.size

    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        holder.bind(teachers[position])
    }

    inner class TeacherViewHolder(private val binding: ItemTeacherBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(teacher: TeacherResponse) {
            with(binding) {
                textName.text = "${teacher.name} ${teacher.last_name}"
                Glide.with(imageTeacher.context)
                    .load(teacher.imageUrl)
                    .into(imageTeacher)

                root.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${teacher.phone}")
                    context.startActivity(intent)
                }

                root.setOnLongClickListener {
                    val intent = Intent(Intent.ACTION_SENDTO).apply {
                        data = Uri.parse("mailto:${teacher.email}")
                        putExtra(Intent.EXTRA_SUBJECT, context.getString(com.gonzales.liam.laboratoriocalificado03.R.string.email_subject))
                    }
                    try {
                        context.startActivity(intent)
                    } catch (ex: Exception) {
                        Toast.makeText(context, context.getString(com.gonzales.liam.laboratoriocalificado03.R.string.email_error), Toast.LENGTH_SHORT).show()
                    }
                    true
                }
            }
        }
    }
}