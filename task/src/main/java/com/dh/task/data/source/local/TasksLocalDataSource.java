package com.dh.task.data.source.local;

import androidx.annotation.NonNull;

import com.dh.core.callback.DataCallback;
import com.dh.task.data.Task;
import com.dh.task.data.source.TasksDataSource;

import java.util.List;

/**
 * Created by Jin on 2020/10/26.
 * Description 本地数据源
 */
public class TasksLocalDataSource implements TasksDataSource {

    private static TasksLocalDataSource INSTANCE;

    public static TasksLocalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksLocalDataSource();
        }
        return INSTANCE;
    }

    private TasksLocalDataSource() {}

    @Override
    public void getTasks(@NonNull DataCallback<List<Task>> callback) {
        callback.onDataNotAvailable();
    }

    @Override
    public void saveTask(@NonNull Task task) {

    }
}
