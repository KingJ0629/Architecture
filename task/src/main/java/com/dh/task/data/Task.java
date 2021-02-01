package com.dh.task.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * model class for a Task.
 */
public class Task {

    private String id;

    private String title;

    private String description;

    private boolean completed;

    public Task(@Nullable String title, @Nullable String description,
                @NonNull String id) {
        this.id = id;
        this.title = title;
        this.description = description;
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Task with title " + title;
    }
}
