package com.example.oasistask2;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.oasistask2.sql.TaskDBHelper;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskViewHolder> {

    private Context context;
    private ArrayList<Tasks> listTasks;
    private ArrayList<Tasks> mArrayList;

    private TaskDBHelper taskDBHelper;

    public TaskAdapter(Context context, ArrayList<Tasks> listTasks) {
        this.context = context;
        this.listTasks = listTasks;
        this.mArrayList= listTasks;
        taskDBHelper = new TaskDBHelper(context);
    }

    public TaskViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TaskViewHolder holder, int position) {
        final Tasks tasks = listTasks.get(position);

        holder.task.setText(tasks.getTask());
        holder.doneOrNot.setImageResource(R.drawable.task_done_lyt);
//        holder..setText(tasks.ge());

//        holder.editContact.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                editTaskDialog(contacts);
//            }
//        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //delete row from database

                taskDBHelper.deleteTask(tasks.getId());

                //refresh the activity page.

                ((Activity)context).finish();
                ((Activity)context).overridePendingTransition(0,0);
                context.startActivity(((Activity) context).getIntent());
                ((Activity)context).overridePendingTransition(0,0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listTasks.size();
    }

}
