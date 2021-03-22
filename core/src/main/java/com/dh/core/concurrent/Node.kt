package com.dh.core.concurrent

import android.util.Log

/**
 * Created by Jin on 2021/3/19.
 * Description 节点任务
 */
class Node constructor(private val runnable: Runnable, val name: String = "节点任务") {

    private val front: MutableList<Node> = mutableListOf() // 前置任务
    private var inDegree: Int = front.size // 任务入度
    private lateinit var mCallback: Callback

    fun bubble(node: Node): Node? {
        if (front.contains(node)) {
            inDegree--
        }
        return if (ready()) this
        else null
    }

    fun dependOn(vararg nodes: Node) {
        nodes.forEach {
            if (front.contains(it))
                throw RuntimeException("重复依赖！")
            front.add(it)
            inDegree++
        }
    }

    fun ready(): Boolean {
        return inDegree == 0
    }

    fun run(mCallback: Callback) {
        this.mCallback = mCallback
        Thread(RunnableWrapper()).start()
    }

    inner class RunnableWrapper : Runnable {
        override fun run() {
            runnable.run()
            Log.i(Parallel.TAG, "$name is end")
            mCallback.success()
        }
    }

    interface Callback {
        fun success()
    }
}