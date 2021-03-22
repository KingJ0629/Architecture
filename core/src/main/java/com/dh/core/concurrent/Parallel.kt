package com.dh.core.concurrent

import android.util.Log
import java.util.*
import java.util.concurrent.CountDownLatch
import kotlin.concurrent.thread

/**
 * Created by Jin on 2021/3/19.
 * Description 平行线
 */
class Parallel constructor(private val nodeList: MutableList<Node>) {

    companion object {
        const val TAG = "Parallel"
    }

    private val length = nodeList.size
    private var visitor = 0 // 当前启动的任务总数
    private val countDownLatch: CountDownLatch = CountDownLatch(length)

    private val queue: Queue<Node> = LinkedList()

    fun start(callback: Node.Callback? = null) {
        thread {
            val mIterator = nodeList.iterator()
            mIterator.forEach {
                if (it.ready()) {
                    queue.offer(it)
                    mIterator.remove()
                }
            }

            bubble()
            countDownLatch.await()
            callback?.success()
        }
    }

    /**
     * 冒泡(广度优先算法)
     */
    private fun bubble() {
        while(!queue.isEmpty()) {
            val top: Node = queue.poll()
            Log.i(TAG, "${top.name} is run")
            visitor++ // 启动的任务总数
            top.run(object: Node.Callback {
                override fun success() {
                    val mIterator = nodeList.iterator()
                    mIterator.forEach {
                        val node = it.bubble(top)
                        if (node != null) {
                            queue.offer(node)
                            mIterator.remove()
                        }
                    }
                    countDownLatch.countDown()

                    // 继续冒泡
                    if (!queue.isEmpty()) bubble()
                }
            })
        }

        if (nodeList.size == 0 && visitor != length) {
            throw RuntimeException("不是个有向无环图！")
        }
    }
}