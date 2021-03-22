package com.dh.architecture

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.dh.core.concurrent.Node
import com.dh.core.concurrent.Parallel

/**
 * Created by Jin on 2021/3/22.
 * Description 并行任务测试
 */
class ParallelTestActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val node1 = Node(Runnable {
            Thread.sleep(1000L)
        }, "node 1")

        val node2 = Node(Runnable {
            Thread.sleep(5000L)
        }, "node 2")

        val node3 = Node(Runnable {
            Thread.sleep(1000L)
        }, "node 3")

        val node4 = Node(Runnable {
            Thread.sleep(1000L)
        })

        node4.dependOn(node1, node3)
        node2.dependOn(node1)
        node3.dependOn(node1)

        Parallel(mutableListOf(node4, node1, node2, node3)).start(object: Node.Callback {
            override fun success() {
                Log.i(Parallel.TAG, "end........!")
            }
        })

        Log.i(Parallel.TAG, "end........?")
    }
}