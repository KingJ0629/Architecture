package com.dh.task.data.source;

import androidx.annotation.NonNull;

import com.dh.core.callback.DataCallback;
import com.dh.task.data.Task;

import java.util.List;

/**
 * Created by Jin on 2020/10/26.
 * Description 接口抽象类
 */
public interface TasksDataSource {

    interface getTasksCallback extends DataCallback<Task> {
        // 可以继承或直接使用默认的几种回调方式，也可以自己写具体的interface
    }

    void getTasks(@NonNull DataCallback<List<Task>> callback);

    void saveTask(@NonNull Task task);
}
