package com.dh.task.data.source

import com.dh.core.callback.DataCallback
import com.dh.task.data.Task
import com.google.common.base.Preconditions
import javax.inject.Inject
import javax.inject.Named

/**
 * Created by Jin on 2021/2/24.
 * Description
 */
class TasksRepository @Inject constructor(@Named("remote") tasksRemoteDataSource : TasksDataSource,
                                          @Named("local") tasksLocalDataSource : TasksDataSource) : TasksDataSource {

    private val mTasksRemoteDataSource: TasksDataSource = Preconditions.checkNotNull(tasksRemoteDataSource)
    private val mTasksLocalDataSource: TasksDataSource = Preconditions.checkNotNull(tasksLocalDataSource)

    /**
     * 运行时缓存.
     */
    var mCachedTasks: MutableList<Task> = mutableListOf()

    /**
     * 将缓存标记为无效，以便在下一次请求数据时强制更新
     */
    var mCacheIsDirty = true

    /**
     * 数据仓库 负责用不同的策略获取数据
     * 如果业务简单，也可直接实现数据获取；
     * 业务复杂时也可配置成多数据源方式，此时数据仓库负责数据调度。
     * 作为MVVM架构下的M层，把数据源和具体业务分离
     */
    override fun getTasks(callback: DataCallback<MutableList<Task>?>) {
        Preconditions.checkNotNull(callback)

        if (!mCacheIsDirty) {
            callback.onDataLoaded(mCachedTasks)
            return
        }
        if (mCacheIsDirty) {
            getTasksFromRemoteDataSource(callback)
        } else {
            mTasksLocalDataSource.getTasks(object : DataCallback<MutableList<Task>?> {
                override fun onDataLoaded(data: MutableList<Task>?) {
                    data?.let { callback.onDataLoaded(it) }
                }

                override fun onDataNotAvailable() {
                    getTasksFromRemoteDataSource(callback)
                }
            })
        }
    }

    private fun getTasksFromRemoteDataSource(callback: DataCallback<MutableList<Task>?>) {
        mTasksRemoteDataSource.getTasks(object : DataCallback<MutableList<Task>?> {
            override fun onDataLoaded(data: MutableList<Task>?) {
                if (data == null) {
                    callback.onDataNotAvailable()
                    return
                }

                mCachedTasks.clear()
                mCachedTasks.addAll(data)
                mCacheIsDirty = false
                callback.onDataLoaded(mCachedTasks)
            }

            override fun onDataNotAvailable() {
                callback.onDataNotAvailable()
            }
        })
    }

    override fun saveTask(task: Task) {
    }
}