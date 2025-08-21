package com.example.myapplication.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.data.local.model.TaskEntity
import com.example.myapplication.data.model.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: TaskEntity)

    @Query("SELECT * FROM task ORDER BY isChecked ASC")
    suspend fun getAllTasks(): List<Task>

    @Query("DELETE FROM task WHERE id = :taskId")
    suspend fun deleteTaskById(taskId: Int)

    @Query("UPDATE task SET isChecked = NOT isChecked WHERE id = :taskId")
    suspend fun toggleTaskChecked(taskId: Int)
}