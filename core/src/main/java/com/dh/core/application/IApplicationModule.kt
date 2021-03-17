package com.dh.core.application

/**
 * Created by Jin on 2021/3/17.
 * Description
 */
interface IApplicationModule {

    companion object {
        val applicationImpl: IApplicationImpl = IApplicationImpl()
    }

    class IApplicationImpl {
        private val initArray: MutableList<ApplicationModule> = mutableListOf()

        fun register(iApplicationModule: IApplicationModule, priority: Int) {
            initArray.add(ApplicationModule(iApplicationModule, priority))
        }

        fun execAllInit() {
            initArray.sort()
            initArray.forEach { it.module.init() }
        }
    }

    data class ApplicationModule(val module: IApplicationModule, val priority: Int):
        Comparable<ApplicationModule> {
        override fun compareTo(other: ApplicationModule): Int {
            return priority - other.priority
        }
    }

    fun register(impl: IApplicationImpl) {
        impl.register(this, 99)
    }

    fun init()
}