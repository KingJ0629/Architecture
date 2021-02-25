package com.dh.task.serviceimpl

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.dh.task_api.service.HelloService

/**
 * Created by Jin on 2021/2/25.
 * Description 实现服务接口
 */
@Route(path = "/dh/hello", name = "测试服务")
class HelloServiceImpl: HelloService {

    override fun sayHello(name: String): String {
        return "hello, $name"
    }

    override fun init(context: Context) {
    }
}