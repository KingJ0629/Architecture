package com.dh.task.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dh.task.R;
import com.dh.task.databinding.TasksActivityBinding;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

/**
 * Created by Jin on 2020/10/27.
 * Description
 */
@AndroidEntryPoint
public class TaskDetailActivity extends AppCompatActivity {

    @Inject
    TaskViewModel viewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TasksActivityBinding binding = DataBindingUtil.setContentView(this, R.layout.tasks_activity);

        binding.setViewModel(viewModel);
        viewModel.loadTasks();
    }
}
