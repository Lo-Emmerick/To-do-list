package com.example.myapplication.domain.usecase

import com.example.myapplication.data.model.Task
import com.example.myapplication.domain.model.toTaskList
import com.example.myapplication.domain.repository.HomeRepository
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class AddTaskUseCaseTest {

    private lateinit var addTaskUseCase: AddTaskUseCase
    private val repository: HomeRepository = mockk()

    private val tasks = listOf(
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
    )

    private val expectedTaskList = tasks.toTaskList()

    @Before
    fun setUp() {
        addTaskUseCase = AddTaskUseCase(repository)
    }

    @Test
    fun `invoke deve retornar TaskList atualizado quando addTask for chamado`() = runTest {

        val task = "Teste 1"

        coEvery { repository.addTask(task) } returns tasks

        val result = addTaskUseCase(task)

        assertEquals(expectedTaskList, result)
    }
}