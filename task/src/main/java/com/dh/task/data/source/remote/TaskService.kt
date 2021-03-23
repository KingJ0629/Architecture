package com.dh.task.data.source.remote

import com.dh.task_api.entity.Task
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by Jin on 2021/2/24.
 * Description
 */
interface TaskService {

    @GET("group/{id}/users")
    fun taskList(@Path("id") taskId: Int): Call<MutableList<Task>>

    @GET("group/{id}/users")
    suspend fun taskListCoroutine(@Path("id") taskId: Int): Call<MutableList<Task>>
}