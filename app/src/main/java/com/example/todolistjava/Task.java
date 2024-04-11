package com.example.todolistjava;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import kotlin.text.UStringsKt;

@Entity(tableName = "tasks")
public class Task {

    // Attributes

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="ID")
    private Integer taskId;

    @NonNull
    @ColumnInfo(name="Name")
    private String taskName;

    @NonNull
    @ColumnInfo(name="DueDate")
    private String taskDueDate;

    @NonNull
    @ColumnInfo(name="Priority")
    private String taskPriority;

    @NonNull
    @ColumnInfo(name="Colour")
    private String taskColour;

    // Getters and Setters

    @NonNull
    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(@NonNull Integer taskId) {
        this.taskId = taskId;
    }

    @NonNull
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(@NonNull String taskName) {
        this.taskName = taskName;
    }

    @NonNull
    public String getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(@NonNull String taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    @NonNull
    public String getTaskPriority() {
        return taskPriority;
    }

    public void setTaskPriority(@NonNull String taskPriority) {
        this.taskPriority = taskPriority;
    }

    @NonNull
    public String getTaskColour() {
        return taskColour;
    }

    public void setTaskColour(@NonNull String taskColour) {
        this.taskColour = taskColour;
    }

    // toString method

    @Override
    public String toString() {
        return "Task{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDueDate='" + taskDueDate + '\'' +
                ", taskPriority='" + taskPriority + '\'' +
                ", taskColour='" + taskColour + '\'' +
                '}';
    }

    // Constructor

    public Task(@NonNull String taskName, @NonNull String taskDueDate, @NonNull String taskPriority, @NonNull String taskColour) {
        this.taskName = taskName;
        this.taskDueDate = taskDueDate;
        this.taskPriority = taskPriority;
        this.taskColour = taskColour;
    }

}



