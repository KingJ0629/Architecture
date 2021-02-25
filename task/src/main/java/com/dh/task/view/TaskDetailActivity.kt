package com.dh.task.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alibaba.android.arouter.launcher.ARouter
import com.dh.core.Constants
import com.dh.task.R
import com.dh.task.databinding.TasksActivityBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.tasks_activity.*
import javax.inject.Inject

/**
 * Created by Jin on 2021/2/25.
 * Description
 */
@AndroidEntryPoint
class TaskDetailActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: TasksActivityBinding = DataBindingUtil.setContentView(this, R.layout.tasks_activity)
        binding.viewModel = viewModel
        viewModel.loadTasks()

        clickLayout.setOnClickListener {
            ARouter.getInstance().build(Constants.TEST_ACTIVITY).navigation()
        }
    }
}