package com.example.todolistjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_TASK_ACTIVITY_REQUEST_CODE = 1;
    public static final int EDIT_TASK_ACTIVITY_REQUEST_CODE = 2;
    private TaskViewModel mTaskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final TaskListAdapter adapter = new TaskListAdapter(new TaskListAdapter.AssignmentDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mTaskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);

        List<String> priorities = mTaskViewModel.getAllPriority();

        Spinner dropdown = findViewById(R.id.priorityDropDown);
        priorities.add(0, "All");
        ArrayAdapter<String> dropdownAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, priorities);
        dropdown.setAdapter(dropdownAdapter);

        MainActivity currentThis = this;
        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String priority = priorities.get(i);
                if (priority == "All") {
                    mTaskViewModel.getAllTasks().observe(currentThis, tasks -> {
                        adapter.submitList(tasks);
                        adapter.setTasks(tasks);
                    });
                } else {
                    mTaskViewModel.getTaskByPriority(priority).observe(currentThis, tasks -> {
                        adapter.setTasks(tasks);
                        adapter.submitList(tasks);
                    });
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                return;
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, NewTaskActivity.class);
            startActivityForResult(intent, NEW_TASK_ACTIVITY_REQUEST_CODE);
        });

        FloatingActionButton fabCalendar = findViewById(R.id.fabOpenCalendar);
        fabCalendar.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, TaskCalendarActivity.class);
            startActivityForResult(intent, 3);
        });

        ItemTouchHelper helper = new ItemTouchHelper(
                new ItemTouchHelper.SimpleCallback(0,
                        ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

                    @Override
                    public boolean onMove(RecyclerView recyclerview,
                                          RecyclerView.ViewHolder viewHolder,
                                          RecyclerView.ViewHolder target) {
                        return false;
                    }

                    @Override
                    public void onSwiped(RecyclerView.ViewHolder viewHolder,
                                         int direction) {
                        // Swipe Left to delete
                        int position = viewHolder.getAdapterPosition();
                        Task myTask = adapter.getTaskAtPosition(position);
                        if (direction == ItemTouchHelper.LEFT) {
                            Toast.makeText(MainActivity.this, "Deleting " +
                                    myTask.getTaskName(), Toast.LENGTH_LONG).show();

                            mTaskViewModel.deleteTask(myTask);
                        } else if (direction == ItemTouchHelper.RIGHT) {

                            Integer taskID = myTask.getTaskId();
                            String taskName = myTask.getTaskName();
                            String taskDate = myTask.getTaskDueDate();
                            String taskPriority = myTask.getTaskPriority();
                            String taskColour = myTask.getTaskColour();

                            Bundle extras = new Bundle();
                            extras.putInt("EXTRA_ID", taskID);
                            extras.putString("EXTRA_NAME", taskName);
                            extras.putString("EXTRA_DATE", taskDate);
                            extras.putString("EXTRA_PRIORITY", taskPriority);
                            extras.putString("EXTRA_COLOUR", taskColour);

                            Intent intent = new Intent(MainActivity.this, EditTaskActivity.class);
                            intent.putExtras(extras);
                            startActivityForResult(intent, EDIT_TASK_ACTIVITY_REQUEST_CODE, extras);
                            Toast.makeText(MainActivity.this, "Editing " +
                                    myTask.getTaskName(), Toast.LENGTH_LONG).show();
                        }
                    }

                }
        );

        helper.attachToRecyclerView(recyclerView);

    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            String taskName = extras.getString("EXTRA_NAME");
            String taskDate = extras.getString("EXTRA_DATE");
            String taskPriority = extras.getString("EXTRA_PRIORITY");
            String taskColour = extras.getString("EXTRA_COLOUR");

            Task task = new Task(taskName, taskDate, taskPriority, taskColour);
            mTaskViewModel.insert(task);
        } if (requestCode == EDIT_TASK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Integer taskID = extras.getInt("EXTRA_ID");
            String taskName = extras.getString("EXTRA_NAME");
            String taskDate = extras.getString("EXTRA_DATE");
            String taskPriority = extras.getString("EXTRA_PRIORITY");
            String taskColour = extras.getString("EXTRA_COLOUR");

            mTaskViewModel.updateTask(taskID, taskName, taskDate, taskPriority, taskColour);
        }else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.not_saved_empty,
                    Toast.LENGTH_LONG).show();

        }
    }

}