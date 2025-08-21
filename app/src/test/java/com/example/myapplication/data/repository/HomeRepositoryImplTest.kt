package com.example.myapplication.data.repository

import com.example.myapplication.data.local.dao.TaskDao
import com.example.myapplication.data.local.model.TaskEntity
import com.example.myapplication.data.model.Task
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class HomeRepositoryImplTest {
    private lateinit var repository: HomeRepositoryImpl
    private val dao: TaskDao = mockk()

    private val task1 = Task(id = 1, isChecked = false, text = "Task 1")
    private val task2 = Task(id = 2, isChecked = true, text = "Task 2")
    private val tasksList = listOf(task1, task2)

    @Before
    fun setUp() {
        repository = HomeRepositoryImpl(dao)
    }

    @Test
    fun `searchTask deve retornar lista de tasks`() = runTest {

        coEvery { dao.getAllTasks() } returns tasksList

        val result = repository.searchTask()

        assertEquals(tasksList, result)
        coVerify(exactly = 1) { dao.getAllTasks() }
    }

    @Test
    fun `addTask deve inserir task e retornar lista atualizada`() = runTest {

        val taskEntity = TaskEntity(
            id = 0, text = "Nova Task",
            isChecked = false
        )

        coEvery { dao.insertTask(taskEntity) } returns Unit
        coEvery { dao.getAllTasks() } returns tasksList

        val result = repository.addTask(taskEntity)

        assertEquals(tasksList, result)
        coVerify(exactly = 1) { dao.insertTask(taskEntity) }
        coVerify(exactly = 1) { dao.getAllTasks() }
    }

    @Test
    fun `deleteTask deve deletar task e retornar lista atualizada`() = runTest {

        val idToDelete = 1

        coEvery { dao.deleteTaskById(idToDelete) } returns Unit
        coEvery { dao.getAllTasks() } returns listOf(task2)

        val result = repository.deleteTask(idToDelete)

        assertEquals(listOf(task2), result)
        coVerify(exactly = 1) { dao.deleteTaskById(idToDelete) }
        coVerify(exactly = 1) { dao.getAllTasks() }
    }

    @Test
    fun `editCheck deve alternar isChecked e retornar lista atualizada`() = runTest {

        val idToToggle = 1

        coEvery { dao.toggleTaskChecked(idToToggle) } returns Unit
        coEvery { dao.getAllTasks() } returns listOf(task1.copy(isChecked = true), task2)

        val result = repository.editCheck(idToToggle)

        assertEquals(listOf(task1.copy(isChecked = true), task2), result)
        coVerify(exactly = 1) { dao.toggleTaskChecked(idToToggle) }
        coVerify(exactly = 1) { dao.getAllTasks() }
    }
}