package com.dh.test.view

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.dh.core.Constants
import com.dh.task_api.constant.TaskConstants
import com.dh.task_api.service.HelloService
import com.dh.test.R
import kotlinx.android.synthetic.main.test_view.*


/**
 * Created by Jin on 2021/2/25.
 * Description 其他模块-跨模块调用服务测试
 */
@Route(path = Constants.TEST_ACTIVITY)
class TestDetailActivity: AppCompatActivity() {

    @JvmField
    @Autowired
    var helloService: HelloService? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.test_view)

        ARouter.getInstance().inject(this)

        val result = helloService?.sayHello("jack")
        Log.i("msg", result)

        middleView.setOnClickListener { ARouter.getInstance().build(TaskConstants.TaskDetailActivity).navigation() }
    }

}