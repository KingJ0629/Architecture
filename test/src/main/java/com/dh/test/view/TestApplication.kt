package com.dh.test.view

import android.util.Log
import com.dh.core.application.IApplicationModule
import com.google.auto.service.AutoService

/**
 * Created by Jin on 2021/3/17.
 * Description
 */
@AutoService(IApplicationModule::class)
class TestApplication : IApplicationModule {

    override fun register(impl: IApplicationModule.IApplicationImpl) {
        impl.register(this, 1)
    }

    override fun init() {
        Log.i("247144", "TestApplication init")
    }
}