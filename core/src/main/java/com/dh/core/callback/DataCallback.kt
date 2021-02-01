package com.dh.core.callback

/**
 * Created by Jin on 2021/2/1.
 * Description
 */
open interface DataCallback<T> {
    fun onDataLoaded(data: T)
    fun onDataNotAvailable()
}