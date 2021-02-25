package com.dh.task_api.service

import com.alibaba.android.arouter.facade.template.IProvider

/**
 * Created by Jin on 2021/2/25.
 * Description 通过依赖注入解耦:服务管理(一) 暴露服务
 */
interface HelloService: IProvider {

    fun sayHello(name: String): String
}