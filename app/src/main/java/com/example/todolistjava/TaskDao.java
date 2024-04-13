package com.example.todolistjava;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Task task);

    @Query("DELETE FROM tasks")
    void deleteAll();

    @Query("SELECT * FROM tasks ORDER BY Name ASC")
    LiveData<List<Task>> getAlphabetisedTasks();

    @Delete
    void deleteTask(Task task);

    @Query("UPDATE tasks SET Name =:newName, DueDate =:newDate, Priority =:newPriority, Colour =:newColour WHERE ID = :id")
    void updateTask(Integer id, String newName, String newDate, String newPriority, String newColour);

    @Query("SELECT DueDate, Colour from tasks")
    List<DateColour> getDueDates();

    @Update
    void update(Task task);

    @Query("SELECT DISTINCT Priority FROM tasks")
    List<String> getAllPriority();

    @Query("SELECT * FROM tasks WHERE Priority =:priority")
    LiveData<List<Task>> getTaskByPriority(String priority);

}
