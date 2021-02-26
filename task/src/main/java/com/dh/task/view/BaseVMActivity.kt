package com.dh.task.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

/**
 * Created by Jin on 2021/2/25.
 * Description
 */
abstract class BaseVMActivity<T>: AppCompatActivity() {

    var viewModel: TaskViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = getViewModel()
    }

    abstract fun <T : ViewModel> createViewModel(): T

    private inline fun <reified T : ViewModel> getViewModel(): T? {
        return ViewModelProvider(this, BaseViewModelProvider()).get(T::class.java)
    }

    inner class BaseViewModelProvider : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return createViewModel()
        }
    }
}