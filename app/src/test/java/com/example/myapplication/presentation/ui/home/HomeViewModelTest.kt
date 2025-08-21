package com.example.myapplication.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.myapplication.data.model.Task
import com.example.myapplication.domain.model.TaskList
import com.example.myapplication.domain.usecase.AddTaskUseCase
import com.example.myapplication.domain.usecase.DeleteTaskUseCase
import com.example.myapplication.domain.usecase.EditCheckUseCase
import com.example.myapplication.domain.usecase.SearchTaskUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeViewModelTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val dispatcher = StandardTestDispatcher()
    private lateinit var viewModel: HomeViewModel
    private val searchTaskUseCase: SearchTaskUseCase = mockk()
    private val deleteTaskUseCase: DeleteTaskUseCase = mockk()
    private val addTaskUseCase: AddTaskUseCase = mockk()
    private val editCheckUseCase: EditCheckUseCase = mockk()

    @Before
    fun setUp() {
        Dispatchers.setMain(dispatcher)
        viewModel = HomeViewModel(
            searchTaskUseCase,
            deleteTaskUseCase,
            addTaskUseCase,
            editCheckUseCase
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    private val taskList = TaskList(
        tasks = listOf(
            Task(
                id = 1,
                isChecked = true,
                text = "Teste"
            ),
            Task(
                id = 2,
                isChecked = false,
                text = "Teste2"
            )
        ),
        createdTask = 1,
        checkedTask = 0
    )

    private val task = Task(
        id = 1,
        isChecked = true,
        text = "Teste"
    )

    @Test
    fun `searchTask deve retornar lista de estado de sucesso`() = runTest {

        coEvery { searchTaskUseCase() } returns taskList

        viewModel.searchTask()
        advanceUntilIdle()

        assertEquals(HomeState.Success(taskList), viewModel.state.value)
    }

    @Test
    fun `searchTask deve retornar estado de vazio`() = runTest {

        val emptySearchTask = TaskList(
            tasks = emptyList(),
            createdTask = 1,
            checkedTask = 0,
        )

        coEvery { searchTaskUseCase() } returns emptySearchTask

        viewModel.searchTask()
        advanceUntilIdle()

        assertEquals(HomeState.Empty, viewModel.state.value)
    }

    @Test
    fun `searchTask deve retornar estado de erro`() = runTest {

        coEvery { searchTaskUseCase() } throws Exception()

        viewModel.searchTask()
        advanceUntilIdle()

        assertEquals(HomeState.Error, viewModel.state.value)
    }

    @Test
    fun `searchTask deve retornar estado de loadin`() = runTest {

        coEvery { searchTaskUseCase() } returns taskList

        viewModel.searchTask()

        assertEquals(HomeState.Loading, viewModel.state.value)
    }

    @Test
    fun `deleteTask deve retornar uam lista de estado de sucesso`() = runTest {

        coEvery { deleteTaskUseCase(task) } returns taskList

        viewModel.deleteTask(task)
        advanceUntilIdle()

        assertEquals(HomeState.Success(taskList), viewModel.state.value)
    }

    @Test
    fun `deleteTask deve retornar estado de vazio`() = runTest {

        val emptyDeleteTask = TaskList(
            tasks = emptyList(),
            createdTask = 1,
            checkedTask = 0,
        )

        coEvery { deleteTaskUseCase(task) } returns emptyDeleteTask

        viewModel.deleteTask(task)
        advanceUntilIdle()

        assertEquals(HomeState.Empty, viewModel.state.value)
    }

    @Test
    fun `deleteTask deve retornar estado de erro`() = runTest {

        coEvery { deleteTaskUseCase(task) } throws Exception()

        viewModel.deleteTask(task)
        advanceUntilIdle()

        assertEquals(HomeState.Error, viewModel.state.value)
    }

    @Test
    fun `deleteTask deve retornar estado de loadin`() = runTest {

        coEvery { deleteTaskUseCase(task) } returns taskList

        viewModel.deleteTask(task)

        assertEquals(HomeState.Loading, viewModel.state.value)
    }

    @Test
    fun `addTask deve retornar lista de estado de sucesso`() = runTest {

        val stringTask = "Teste"

        coEvery { addTaskUseCase(stringTask) } returns taskList

        viewModel.addTask(stringTask)
        advanceUntilIdle()

        assertEquals(HomeState.Success(taskList), viewModel.state.value)
    }

    @Test
    fun `addTask deve retornar estado de erro`() = runTest {

        val stringTask = "Teste"

        coEvery { addTaskUseCase(stringTask) } throws Exception()

        viewModel.addTask(stringTask)
        advanceUntilIdle()

        assertEquals(HomeState.Error, viewModel.state.value)
    }

    @Test
    fun `addTask deve retornar estado de loadin`() = runTest {

        val stringTask = "Teste"

        coEvery { addTaskUseCase(stringTask) } returns taskList

        viewModel.addTask(stringTask)

        assertEquals(HomeState.Loading, viewModel.state.value)
    }

    @Test
    fun `editCheck deve retornar lista de estado de sucesso`() = runTest {

        coEvery { editCheckUseCase(task) } returns taskList

        viewModel.editCheck(task)
        advanceUntilIdle()

        assertEquals(HomeState.Success(taskList), viewModel.state.value)
    }

    @Test
    fun `editCheck deve retornar estado de erro`() = runTest {

        coEvery { editCheckUseCase(task) } throws Exception()

        viewModel.editCheck(task)
        advanceUntilIdle()

        assertEquals(HomeState.Error, viewModel.state.value)
    }

    @Test
    fun `editCheck deve retornar estado de loadin`() = runTest {

        coEvery { editCheckUseCase(task) } returns taskList

        viewModel.editCheck(task)

        assertEquals(HomeState.Loading, viewModel.state.value)
    }
}