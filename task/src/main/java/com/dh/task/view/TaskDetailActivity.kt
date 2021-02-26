package com.dh.task.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.launcher.ARouter
import com.dh.core.Constants
import com.dh.task.R
import com.dh.task.data.source.TasksRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.tasks_activity.*
import javax.inject.Inject

/**
 * Created by Jin on 2021/2/25.
 * Description
 */
@AndroidEntryPoint
class TaskDetailActivity : BaseVMActivity<TaskViewModel>() {

    @Inject
    lateinit var tasksRepository: TasksRepository

    override fun <T : ViewModel> createViewModel(): T {
        return TaskViewModel(tasksRepository) as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_activity)

        viewModel?.description?.observe(this, Observer {
            descriptionView.text = it
        })
        viewModel?.loadTasks()

        clickLayout.setOnClickListener {
            ARouter.getInstance().build(Constants.TEST_ACTIVITY).navigation()
        }
    }
}