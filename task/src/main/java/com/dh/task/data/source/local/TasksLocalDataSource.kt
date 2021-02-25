package com.dh.task.data.source.local

import com.dh.core.callback.DataCallback
import com.dh.task.data.Task
import com.dh.task.data.source.TasksDataSource
import javax.inject.Inject

/**
 * Created by Jin on 2021/2/24.
 * Description 本地数据源
 */
class TasksLocalDataSource @Inject constructor() : TasksDataSource {

    override fun getTasks(callback: DataCallback<MutableList<Task>?>) {
        callback.onDataNotAvailable()
    }

    override fun saveTask(task: Task) {
    }
}