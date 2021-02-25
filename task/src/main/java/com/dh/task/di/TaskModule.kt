package com.dh.task.di

import com.dh.task.data.source.TasksDataSource
import com.dh.task.data.source.local.TasksLocalDataSource
import com.dh.task.data.source.remote.TaskService
import com.dh.task.data.source.remote.TasksRemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import retrofit2.Retrofit
import javax.inject.Named

/**
 * Created by Jin on 2021/2/25.
 * Description
 */
@Module
@InstallIn(ActivityComponent::class)
abstract class TaskModule {

    @Binds
    @Named("remote")
    abstract fun bindTasksRemoteDataSource(remote: TasksRemoteDataSource): TasksDataSource

    @Binds
    @Named("local")
    abstract fun bindTasksLocalDataSource(local: TasksLocalDataSource): TasksDataSource

    companion object {
        @Provides
        fun provideTaskService(retrofit: Retrofit): TaskService {
            return retrofit.create(TaskService::class.java)
        }
    }
}