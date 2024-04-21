package com.example.todolistjava;

import android.graphics.Color;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import java.util.List;

public class TaskListAdapter extends ListAdapter<Task, TaskViewHolder> {

    private List<Task> mTasks;

    public TaskListAdapter(@NonNull DiffUtil.ItemCallback<Task> diffCallback) {
        super(diffCallback);
    }

    @Override
    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return TaskViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        Task current = getItem(position);
        try {
            holder.bind(current);
            String eventColour = mTasks.get(position).getTaskColour();
            holder.itemView.setBackgroundColor(Color.parseColor(eventColour));
        } catch(IndexOutOfBoundsException e){
            holder.bind(current);
        }
    }

    void setTasks(List<Task> tasks) {
        mTasks = tasks;
        notifyDataSetChanged();
    }

    static class AssignmentDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTaskName().equals(newItem.getTaskName());
        }

    }

    public Task getTaskAtPosition (int position) {
        return mTasks.get(position);
    }

}
