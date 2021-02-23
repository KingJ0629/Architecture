package com.dh.task.data.source.remote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jin on 2021/2/23.
 * Description
 */
public interface TaskService {

    @GET("group/{id}/users")
    Call<List<String>> taskList(@Path("id") int taskId);
}
