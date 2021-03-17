package com.dh.core.application

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import dagger.hilt.android.HiltAndroidApp
import java.util.ServiceLoader

/**
 * Created by Jin on 2021/2/25.
 * Description
 */
@HiltAndroidApp
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

//        ARouter.openLog();     // 打印日志
//        ARouter.openDebug()
        ARouter.init(this)

        // 注册并调用所有子Module的初始化
        val loader: List<IApplicationModule> =
            ServiceLoader.load(IApplicationModule::class.java).toList()
        loader.forEach { it.register(IApplicationModule.applicationImpl) }

        IApplicationModule.applicationImpl.execAllInit()
    }
}