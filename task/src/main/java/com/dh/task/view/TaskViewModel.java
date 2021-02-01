package com.dh.task.view;

import android.content.Context;

import androidx.databinding.BaseObservable;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableList;

import com.dh.task.data.Task;
import com.dh.task.data.source.TasksRepository;
import com.dh.core.callback.DataCallback;

import java.util.List;

/**
 * Created by Jin on 2020/10/27.
 * Description VM
 */
public class TaskViewModel extends BaseObservable {

    public final ObservableList<Task> items = new ObservableArrayList<>();

    public final ObservableField<String> description = new ObservableField<>();

    private TasksRepository mTasksRepository;

    private Context mContext;

    public TaskViewModel(Context context, TasksRepository tasksRepository) {
        // 必须是ApplicationContext 因为viewModel的生命周期要大于activity的生命周期
        mContext = context.getApplicationContext();
        mTasksRepository = tasksRepository;
    }

    public void loadTasks() {
        mTasksRepository.getTasks(new DataCallback<List<Task>>() {

            @Override
            public void onDataLoaded(List<Task> data) {
                items.clear();
                items.addAll(data);
                description.set(items.get(0).getDescription());
            }

            @Override
            public void onDataNotAvailable() {
                // do someThings
            }
        });
    }
}
