package com.example.myapplication.presentation.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.Visibility
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.myapplication.R
import com.example.myapplication.data.model.Task
import com.example.myapplication.domain.model.TaskList
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module

class HomeActivityInstrumentedTest {
    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mockViewModel = mockk<HomeViewModel>(relaxed = true)
    private val stateLiveData = MutableLiveData<HomeState>()

    @Before
    fun setup() {
        stopKoin()

        every { mockViewModel.state } returns stateLiveData

        val testModule = module { single { mockViewModel } }
        startKoin { modules(testModule) }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun showLoadingState() {
        ActivityScenario.launch(HomeActivity::class.java).onActivity { _ ->
            stateLiveData.value = HomeState.Loading
        }

        onView(withId(R.id.state_loading)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerTasks)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun showErrorState() {
        ActivityScenario.launch(HomeActivity::class.java).onActivity { _ ->
            stateLiveData.value = HomeState.Error
        }

        onView(withId(R.id.state_error)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerTasks)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun showEmptyState() {
        ActivityScenario.launch(HomeActivity::class.java).onActivity { _ ->
            stateLiveData.value = HomeState.Empty
        }

        onView(withId(R.id.state_empty)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerTasks)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }

    @Test
    fun showSuccessState() {
        val tasks = listOf(
            Task(1, false, "Task 1"),
            Task(2, true, "Task 2")
        )
        val taskList = TaskList(
            createdTask = tasks.size,
            checkedTask = tasks.count { it.isChecked },
            tasks = tasks
        )

        ActivityScenario.launch(HomeActivity::class.java).onActivity { _ ->
            stateLiveData.value = HomeState.Success(taskList)
        }

        onView(withId(R.id.recyclerTasks)).check(matches(isDisplayed()))
        onView(withId(R.id.tvCreatedCount)).check(matches(withText("2")))
        onView(withId(R.id.tvDoneCount)).check(matches(withText("1")))
    }
}