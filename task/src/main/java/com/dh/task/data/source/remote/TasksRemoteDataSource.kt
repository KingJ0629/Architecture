package com.dh.task.data.source.remote

import com.dh.core.callback.DataCallback
import com.dh.task_api.entity.Task
import com.dh.task.data.source.TasksDataSource
import com.google.common.collect.Lists
import javax.inject.Inject

/**
 * Created by Jin on 2021/2/24.
 * Description 远程数据源
 */
class TasksRemoteDataSource @Inject constructor(taskService: TaskService) : TasksDataSource {

    private val mTaskService: TaskService = taskService
    private val mTasksServiceData: MutableMap<String, Task> = LinkedHashMap(16)

    init {
        addTask("Build tower in Pisa", "Ground looks good, no foundation work required.","0")
        addTask("Build tower in Pisa_", "Ground looks good, no foundation work required_.","1")
    }

    override fun getTasks(callback: DataCallback<MutableList<Task>?>) {
        callback.onDataLoaded(Lists.newArrayList(mTasksServiceData.values))

        mTaskService.taskList(1)

        /********************* Retrofit请求伪代码 ***************************************************


         RetrofitHelper.call(mTaskService.taskList(1), CallBack() {
            override fun success(list: MutableList<Task>) {
                list.forEach {
                    it.hasTitle()
                }
            }

            override fun error(code: String) {
                if (code === 10086) {}
            }
         })


         ******************************************************************************************/
    }

    override fun saveTask(task: Task) {
    }

    private fun addTask(title: String, description: String, id: String) {
        val newTask = Task(id, title, description)
        mTasksServiceData[newTask.id] = newTask
    }
}