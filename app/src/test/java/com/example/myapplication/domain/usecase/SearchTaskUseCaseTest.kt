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

class SearchTaskUseCaseTest {

    private lateinit var searchTaskUseCase: SearchTaskUseCase
    private val repository: HomeRepository = mockk()

    private val tasksFromRepository = listOf(
        Task(
            id = 1,
            isChecked = false,
            text = "Teste 1"
        ),
        Task(
            id = 2,
            isChecked = true,
            text = "Teste 2"
        )
    )

    private val expectedTaskList = tasksFromRepository.toTaskList()

    @Before
    fun setUp() {
        searchTaskUseCase = SearchTaskUseCase(repository)
    }

    @Test
    fun `invoke deve retornar TaskList atualizado quando searchTask for chamado`() = runTest {

        coEvery { repository.searchTask() } returns tasksFromRepository

        val result = searchTaskUseCase()

        assertEquals(expectedTaskList, result)
    }
}
