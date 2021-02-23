package com.dh.task.data.source.local;

import androidx.annotation.NonNull;

import com.dh.core.callback.DataCallback;
import com.dh.task.data.Task;
import com.dh.task.data.source.TasksDataSource;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Jin on 2020/10/26.
 * Description 本地数据源
 */
public class TasksLocalDataSource implements TasksDataSource {

    @Inject
    public TasksLocalDataSource() {

    }

    @Override
    public void getTasks(@NonNull DataCallback<List<Task>> callback) {
        callback.onDataNotAvailable();
    }

    @Override
    public void saveTask(@NonNull Task task) {

    }
}
