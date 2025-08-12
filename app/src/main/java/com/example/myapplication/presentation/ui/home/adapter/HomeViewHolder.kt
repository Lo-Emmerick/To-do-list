package com.example.myapplication.presentation.ui.home.adapter

import android.graphics.Paint
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.Task
import com.example.myapplication.databinding.ItemActivityBinding

class HomeViewHolder(
    private val binding: ItemActivityBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Task) {
        bindView(item)
    }

    private fun bindView(item: Task) {
        if (item.isChecked == true){
            binding.imgTaskCheck.isVisible = true
            binding.tvTaskText.text = item.text
            binding.tvTaskText.paintFlags = binding.tvTaskText.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            binding.tvTaskText.paintFlags = binding.tvTaskText.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            binding.tvTaskText.text = item.text
        }
    }
}