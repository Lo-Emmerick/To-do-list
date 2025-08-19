package com.example.myapplication.presentation.ui.home.adapter

import android.graphics.Paint
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.model.Task
import com.example.myapplication.databinding.ItemActivityBinding

class HomeViewHolder(
    private val binding: ItemActivityBinding,
    private val listener: HomeListener
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Task) {
        bindView(item)
        bindinglistener(item)
    }

    private fun bindView(item: Task) {
        binding.tvTaskText.text = item.text
        binding.check.isChecked = item.isChecked
        if (item.isChecked){
            binding.tvTaskText.paintFlags = binding.tvTaskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        }
    }

    private fun bindinglistener(item: Task) {
        binding.check.setOnClickListener {
            listener.checked(item)
        }

        binding.btnDelete.setOnClickListener {
            listener.deleteTask(item)
        }
    }
}