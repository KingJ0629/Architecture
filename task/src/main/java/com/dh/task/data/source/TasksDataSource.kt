package com.dh.task.data.source

import com.dh.core.callback.DataCallback
import com.dh.task_api.entity.Task

/**
 * Created by Jin on 2021/2/24.
 * Description 接口抽象类
 */
interface TasksDataSource {

    interface GetTasksCallback : DataCallback<Task> {
        // 可以继承或直接使用默认的几种回调方式，也可以自己写具体的interface
    }

    fun getTasks(callback: DataCallback<MutableList<Task>?>)

    fun saveTask(task: Task)
}