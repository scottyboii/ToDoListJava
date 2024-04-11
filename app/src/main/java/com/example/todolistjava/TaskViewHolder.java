package com.example.todolistjava;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {

    public static final String EXTRA_REPLY_TASK = "com.example.todolistjava.ASSIGNMENT.REPLY";
    private final TextView taskItemView;

    private Task link;

    private TaskViewHolder(View itemView) {
        super(itemView);
        taskItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(Task current) {
        StringBuilder taskText = new StringBuilder();
        taskText.append(current.getTaskId()).append(") ");
        taskText.append(current.getTaskName()).append(" Due: ");
        taskText.append(current.getTaskDueDate()).append(" Priority: ");
        taskText.append(current.getTaskPriority());

        taskItemView.setText(taskText);
        this.link = current;
    }

    static TaskViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_item, parent, false);
        return new TaskViewHolder(view);
    }

}
