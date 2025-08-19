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

class DeleteTaskUseCaseTest {

    private lateinit var deleteTaskUseCase: DeleteTaskUseCase
    private val repository: HomeRepository = mockk()

    private val taskToDelete = Task(
        id = 1,
        isChecked = true,
        text = "Teste"
    )

    private val tasksAfterDelete = listOf(
        Task(
            id = 2,
            isChecked = false,
            text = "Teste2"
        )
    )

    private val expectedTaskList = tasksAfterDelete.toTaskList()

    @Before
    fun setUp() {
        deleteTaskUseCase = DeleteTaskUseCase(repository)
    }

    @Test
    fun `invoke deve retornar TaskList atualizado quando deleteTask for chamado`() = runTest {
        coEvery { repository.deleteTask(taskToDelete) } returns tasksAfterDelete

        val result = deleteTaskUseCase(taskToDelete)

        assertEquals(expectedTaskList, result)
    }
}
