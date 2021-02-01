package com.dh.task.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dh.task.R;
import com.dh.task.data.source.TasksRepository;
import com.dh.task.data.source.local.TasksLocalDataSource;
import com.dh.task.data.source.remote.TasksRemoteDataSource;
import com.dh.task.databinding.TasksActivityBinding;

/**
 * Created by Jin on 2020/10/27.
 * Description
 */
public class TaskDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TasksActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.tasks_activity);

        TaskViewModel viewModel = new TaskViewModel(getApplicationContext(),
                TasksRepository.getInstance(TasksLocalDataSource.getInstance(), TasksRemoteDataSource.getInstance()));

        binding.setViewModel(viewModel);
        viewModel.loadTasks();
    }
}
