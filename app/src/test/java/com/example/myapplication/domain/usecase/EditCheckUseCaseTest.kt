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

class EditCheckUseCaseTest {

    private lateinit var editCheckUseCase: EditCheckUseCase
    private val repository: HomeRepository = mockk()

    private val taskToEdit = Task(
        id = 1,
        isChecked = false,
        text = "Teste"
    )

    private val tasksAfterEdit = listOf(
        Task(
            id = 1,
            isChecked = true,
            text = "Teste"
        ),
        Task(
            id = 2,
            isChecked = false,
            text = "Outro teste"
        )
    )

    private val expectedTaskList = tasksAfterEdit.toTaskList()

    @Before
    fun setUp() {
        editCheckUseCase = EditCheckUseCase(repository)
    }

    @Test
    fun `invoke deve retornar TaskList atualizado quando editCheck for chamado`() = runTest {

        coEvery { repository.editCheck(taskToEdit) } returns tasksAfterEdit

        val result = editCheckUseCase(taskToEdit)

        assertEquals(expectedTaskList, result)
    }
}
