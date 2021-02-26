package com.dh.architecture

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import dagger.hilt.android.HiltAndroidApp

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
    }
}