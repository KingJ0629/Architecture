package com.dh.task.view

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dh.core.Constants
import com.dh.core.base.BaseVMActivity
import com.dh.task.R
import com.dh.task.data.source.TasksRepository
import com.dh.task_api.constant.TaskConstants
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.tasks_activity.*
import javax.inject.Inject

/**
 * Created by Jin on 2021/2/25.
 * Description
 */
@AndroidEntryPoint
@Route(path = TaskConstants.TaskDetailActivity)
class TaskDetailActivity : BaseVMActivity<TaskViewModel>() {

    lateinit var viewModel: TaskViewModel

    @Inject
    lateinit var tasksRepository: TasksRepository

    override fun <T : ViewModel> createViewModel(): T {
        return TaskViewModel(tasksRepository) as T
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tasks_activity)

        viewModel = getViewModel()
        viewModel.description.observe(this, Observer {
            descriptionView.text = it
        })
        viewModel.loadTasks()

        clickLayout.setOnClickListener {
            ARouter.getInstance().build(Constants.TEST_ACTIVITY).navigation()
        }
    }
}