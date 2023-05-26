package com.example.oasistask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oasistask2.sql.TaskDBHelper;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    EditText task1,task2;
    TextView addTask;
    ImageView back;
    String task;

    TaskDBHelper taskDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        task1 = findViewById(R.id.listTxt1);
        addTask = findViewById(R.id.addList);

        taskDBHelper = new TaskDBHelper(this);

        back =  findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                task = task1.getText().toString();

                if (task.isEmpty()){
                    Toast.makeText(MainActivity.this, "please enter the task first", Toast.LENGTH_SHORT).show();
                }else{
                    Tasks tasks = new Tasks(task,false);
                    taskDBHelper.addTasks(tasks);
                    Toast.makeText(MainActivity.this, "Task Added successfully", Toast.LENGTH_SHORT).show();
                }
                task1.setText("");
            }
        });

    }
}