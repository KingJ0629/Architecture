package com.dh.task

import android.util.Log
import com.dh.core.application.IApplicationModule
import com.google.auto.service.AutoService

/**
 * Created by Jin on 2021/3/17.
 * Description
 */
@AutoService(IApplicationModule::class)
class TaskApplication: IApplicationModule {

    override fun init() {
        Log.i("247144", "TaskApplication init")
    }
}