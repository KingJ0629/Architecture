package com.dh.task.view

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dh.core.callback.DataCallback
import com.dh.task.data.source.TasksRepository
import com.dh.task_api.entity.Task

/**
 * Created by Jin on 2021/2/25.
 * Description VM
 */
class TaskViewModel constructor(tasksRepository: TasksRepository): ViewModel() {

    val items: MutableLiveData<MutableList<Task>> by lazy {
        MutableLiveData<MutableList<Task>>()
    }

    val description: MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    private val mTasksRepository: TasksRepository = tasksRepository

    fun loadTasks() {
        mTasksRepository.getTasks(object : DataCallback<MutableList<Task>?> {
            override fun onDataLoaded(data: MutableList<Task>?) {
                data?.let {
                    items.value?.clear()
                    items.value = data
                    description.value = items.value!![0].description
                }
            }

            override fun onDataNotAvailable() {
                // do someThings
            }
        })
    }
}