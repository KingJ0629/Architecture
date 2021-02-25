package com.dh.task.view

import android.content.Context
import androidx.databinding.BaseObservable
import androidx.databinding.ObservableArrayList
import androidx.databinding.ObservableField
import androidx.databinding.ObservableList
import com.dh.core.callback.DataCallback
import com.dh.task_api.entity.Task
import com.dh.task.data.source.TasksRepository
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * Created by Jin on 2021/2/25.
 * Description VM
 */
class TaskViewModel @Inject constructor(@ActivityContext context: Context, tasksRepository: TasksRepository): BaseObservable() {

    val items: ObservableList<Task> = ObservableArrayList()
    val description = ObservableField<String>()

    private val mTasksRepository: TasksRepository = tasksRepository
    private val mContext: Context = context.applicationContext

    fun loadTasks() {
        mTasksRepository.getTasks(object : DataCallback<MutableList<Task>?> {
            override fun onDataLoaded(data: MutableList<Task>?) {
                data?.let {
                    items.clear()
                    items.addAll(data)
                    description.set(items[0].description)
                }
            }

            override fun onDataNotAvailable() {
                // do someThings
            }
        })
    }
}