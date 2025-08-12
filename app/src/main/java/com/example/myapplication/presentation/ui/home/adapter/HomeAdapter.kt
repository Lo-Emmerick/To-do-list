package com.example.moviedb.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.example.myapplication.data.Task
import com.example.myapplication.databinding.ItemActivityBinding
import com.example.myapplication.presentation.ui.home.adapter.HomeListener
import com.example.myapplication.presentation.ui.home.adapter.HomeViewHolder

class HomeAdapter(
    private val items: List<Task>,
    private val listener: HomeListener
) : Adapter<HomeViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            ItemActivityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return items.count()
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(items[position])
    }
}