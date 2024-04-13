package com.example.todolistjava;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

class TaskRepository {

    private TaskDao mTaskDao;
    private LiveData<List<Task>> mAllTasks;

    TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        mTaskDao = db.taskDao();
        mAllTasks = mTaskDao.getAlphabetisedTasks();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    void insert(Task task) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.insert(task);
        });
    }

    private static class deleteTaskAsyncTask extends AsyncTask<Task, Void, Void> {
        private TaskDao mAsyncTaskDao;

        deleteTaskAsyncTask(TaskDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            mAsyncTaskDao.deleteTask(params[0]);
            return null;
        }
    }

    public void deleteTask(Task task) {
        new deleteTaskAsyncTask(mTaskDao).execute(task);
    }

    public void updateTask(Integer taskID, String taskName, String taskDate, String taskPriority, String taskColour) {
        TaskRoomDatabase.databaseWriteExecutor.execute(() -> {
            mTaskDao.updateTask(taskID, taskName, taskDate, taskPriority, taskColour);
        });
    }

    List<String> getDueDates() {
        return mTaskDao.getDueDates();
    }

    List<String> getAllPriority() {
        return mTaskDao.getAllPriority();
    }

    LiveData<List<Task>> getTaskByPriority(String priority) {
        return mTaskDao.getTaskByPriority(priority);
    }

}
