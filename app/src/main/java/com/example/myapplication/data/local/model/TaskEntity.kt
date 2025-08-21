package com.example.myapplication.data.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "task",
    indices = [Index(value = ["text"], unique = true)]
)

class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo(name = "isChecked")
    val isChecked: Boolean,

    @ColumnInfo(name = "text")
    val text: String,
)