package com.dh.task.di;

import com.dh.task.data.source.TasksDataSource;
import com.dh.task.data.source.local.TasksLocalDataSource;
import com.dh.task.data.source.remote.TaskService;
import com.dh.task.data.source.remote.TasksRemoteDataSource;

import javax.inject.Named;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ActivityComponent;
import retrofit2.Retrofit;

/**
 * Created by Jin on 2021/2/23.
 * Description
 */
@Module
@InstallIn(ActivityComponent.class)
public abstract class TaskModule {

    @Binds
    @Named("remote")
    public abstract TasksDataSource bindTasksRemoteDataSource(TasksRemoteDataSource remote);

    @Binds
    @Named("local")
    public abstract TasksDataSource bindTasksLocalDataSource(TasksLocalDataSource local);

    @Provides
    public static TaskService provideTaskService(Retrofit retrofit) {
        return retrofit.create(TaskService.class);
    }
}
