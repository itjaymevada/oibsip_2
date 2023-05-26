package com.example.oasistask2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class TaskViewHolder extends RecyclerView.ViewHolder {
    public TextView task;
    public ImageView doneOrNot;
    public ImageView delete;

    public TaskViewHolder(View itemView){
        super(itemView);
        task = itemView.findViewById(R.id.task_txt);
        doneOrNot = itemView.findViewById(R.id.task_done_img);
        delete = itemView.findViewById(R.id.deleteTask);
    }
}
