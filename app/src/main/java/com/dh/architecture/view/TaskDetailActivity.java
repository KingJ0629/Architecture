package com.dh.architecture.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dh.architecture.R;
import com.dh.architecture.data.source.TasksRepository;
import com.dh.architecture.data.source.local.TasksLocalDataSource;
import com.dh.architecture.data.source.remote.TasksRemoteDataSource;
import com.dh.architecture.databinding.TasksActivityBinding;

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
