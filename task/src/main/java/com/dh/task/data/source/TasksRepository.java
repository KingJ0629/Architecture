package com.dh.task.data.source;

import androidx.annotation.NonNull;

import com.dh.core.callback.DataCallback;
import com.dh.task.data.Task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by Jin on 2020/10/26.
 * Description 数据仓库
 */
public class TasksRepository implements TasksDataSource {

    private final TasksDataSource mTasksRemoteDataSource;
    private final TasksDataSource mTasksLocalDataSource;

    /**
     * 运行时缓存.
     */
    List<Task> mCachedTasks;

    /**
     * 将缓存标记为无效，以便在下一次请求数据时强制更新
     */
    boolean mCacheIsDirty = false;

    @Inject
    public TasksRepository(@Named("remote") TasksDataSource tasksRemoteDataSource,
                           @Named("local") TasksDataSource tasksLocalDataSource) {
        mTasksRemoteDataSource = checkNotNull(tasksRemoteDataSource);
        mTasksLocalDataSource = checkNotNull(tasksLocalDataSource);
    }

    /**
     * 数据仓库 负责用不同的策略获取数据
     * 如果业务简单，也可直接实现数据获取；
     * 业务复杂时也可配置成多数据源方式，此时数据仓库负责数据调度。
     * 作为MVVM架构下的M层，把数据源和具体业务分离
     */
    @Override
    public void getTasks(@NonNull final DataCallback<List<Task>> callback) {
        checkNotNull(callback);

        if (mCachedTasks != null && !mCacheIsDirty) {
            callback.onDataLoaded(mCachedTasks);
            return;
        }

        if (mCacheIsDirty) {
            getTasksFromRemoteDataSource(callback);
        } else {
            mTasksLocalDataSource.getTasks(new DataCallback<List<Task>>() {
                @Override
                public void onDataLoaded(List<Task> data) {
                    callback.onDataLoaded(data);
                }

                @Override
                public void onDataNotAvailable() {
                    getTasksFromRemoteDataSource(callback);
                }
            });
        }
    }

    private void getTasksFromRemoteDataSource(@NonNull final DataCallback<List<Task>> callback) {
        mTasksRemoteDataSource.getTasks(new DataCallback<List<Task>>() {

            @Override
            public void onDataLoaded(List<Task> data) {
                if (mCachedTasks == null) {
                    mCachedTasks = new ArrayList<>();
                }

                if (data == null) {
                    callback.onDataNotAvailable();
                    return;
                }

                mCachedTasks.clear();
                mCachedTasks.addAll(data);
                mCacheIsDirty = false;

                callback.onDataLoaded(mCachedTasks);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }

    @Override
    public void saveTask(@NonNull Task task) {

    }
}
