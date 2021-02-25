package com.dh.task_api.entity

/**
 * Created by Jin on 2021/2/25.
 * Description model class for a Task.
 */
data class Task(var id: String, var title: String, var description: String, var completed: Boolean = false)