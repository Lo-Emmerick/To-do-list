package com.example.myapplication.presentation.ui.home

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.isVisible
import com.example.moviedb.ui.home.adapter.HomeAdapter
import com.example.myapplication.data.model.Task
import com.example.myapplication.databinding.ActivityHomeBinding
import com.example.myapplication.domain.model.TaskList
import com.example.myapplication.presentation.ui.home.adapter.HomeListener
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity(), HomeListener {
    private lateinit var binding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindObserver()
        bindListener()

        viewModel.searchTask()
    }

    private fun bindListener() {
        binding.btnAddTask.setOnClickListener {
            val inputText = binding.inputTask.text.toString()
            viewModel.addTask(inputText)
        }
    }

    private fun bindObserver() {
        viewModel.state.observe(this) {
            setDefaultState()
            when (it) {
                HomeState.Error -> showErrorScreen()
                HomeState.Loading -> showLoadingScreen()
                HomeState.Empty -> showEmptyState()
                is HomeState.Success -> showSuccessScreen(it.taskList)
            }
        }
    }

    private fun setDefaultState() {
        binding.stateEmpty.root.isVisible = false
        binding.stateLoading.root.isVisible = false
        binding.recyclerTasks.isVisible = false
        binding.stateError.root.isVisible = false
    }

    private fun showErrorScreen() {
        binding.stateError.root.isVisible = true
    }

    private fun showSuccessScreen(taskList: TaskList) {
        binding.tvCreatedCount.text = taskList.createdTask.toString()
        binding.tvDoneCount.text = taskList.checkedTask.toString()
        binding.recyclerTasks.isVisible = true
        binding.recyclerTasks.adapter = HomeAdapter(taskList.tasks, this)
    }

    private fun showLoadingScreen() {
        binding.stateLoading.root.isVisible = true
    }

    private fun showEmptyState() {
        binding.stateEmpty.root.isVisible = true
    }

    override fun deleteTask(item: Task) {
        viewModel.deleteTask(item)
    }

    override fun checked(item: Task) {
        viewModel.editCheck(item)
    }
}
