package com.example.todolistjava;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {

    private TaskRepository mRepository;

    private final LiveData<List<Task>> mAllTasks;

    public TaskViewModel (Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    LiveData<List<Task>> getAllTasks() {return mAllTasks; }

    public void insert(Task task) {mRepository.insert(task);}

    public void deleteTask(Task task) {mRepository.deleteTask(task); }

    public void updateTask(Integer taskID, String taskName, String taskDate, String taskPriority, String taskColour) {mRepository.updateTask(taskID, taskName, taskDate, taskPriority, taskColour);}

    List<String> getDueDates() { return mRepository.getDueDates(); }

}
