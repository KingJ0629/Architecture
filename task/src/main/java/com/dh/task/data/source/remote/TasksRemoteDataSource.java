package com.dh.task.data.source.remote;

import androidx.annotation.NonNull;

import com.dh.task.data.Task;
import com.dh.task.data.source.TasksDataSource;
import com.dh.core.callback.DataCallback;
import com.google.common.collect.Lists;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jin on 2020/10/26.
 * Description 远程数据源
 */
public class TasksRemoteDataSource implements TasksDataSource {

    private static TasksRemoteDataSource INSTANCE;

    private final static Map<String, Task> TASKS_SERVICE_DATA;

    static {
        TASKS_SERVICE_DATA = new LinkedHashMap<>(2);
        addTask("Build tower in Pisa", "Ground looks good, no foundation work required.", "0");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "1");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "2");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "3");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "4");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "5");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "6");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "7");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "8");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "12");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "13");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "14");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "15");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "16");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "17");
        addTask("Finish bridge in Tacoma", "Found awesome girders at half the cost!", "18");
    }

    public static TasksRemoteDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TasksRemoteDataSource();
        }
        return INSTANCE;
    }

    private TasksRemoteDataSource() {}

    @Override
    public void getTasks(@NonNull DataCallback<List<Task>> callback) {
        callback.onDataLoaded(Lists.newArrayList(TASKS_SERVICE_DATA.values()));
    }

    @Override
    public void saveTask(@NonNull Task task) {

    }

    private static void addTask(String title, String description, String id) {
        Task newTask = new Task(title, description, id);
        TASKS_SERVICE_DATA.put(newTask.getId(), newTask);
    }
}
