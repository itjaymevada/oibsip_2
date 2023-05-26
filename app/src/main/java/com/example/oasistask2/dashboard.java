package com.example.oasistask2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Process;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oasistask2.sql.DBHelper;
import com.example.oasistask2.sql.TaskDBHelper;

import java.util.ArrayList;

public class dashboard extends AppCompatActivity {

    ImageView logout,addTask;
    TextView welcomeTxt;
    RecyclerView taskList;
    DBHelper dbHelper;
    String Name,Email;
    PreferenceManager preferenceManager;

    long backPressedTime = 0;

    private TaskDBHelper taskDBHelper;
    private ArrayList<Tasks> allTasks =new ArrayList<>();
    private TaskAdapter mAdapter;

    @Override
    public void onBackPressed() {

        if (backPressedTime + 3000 > System.currentTimeMillis()) {
            moveTaskToBack(true);
            Process.killProcess(Process.myPid());
            System.exit(1);
            super.onBackPressed();
        }
        else {
            Toast.makeText(this, "Press back again to exit the app.", Toast.LENGTH_LONG).show();
        }
        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        logout = findViewById(R.id.logout);
        addTask = findViewById(R.id.addtask);
        welcomeTxt = findViewById(R.id.welcomeText);
        taskList = findViewById(R.id.taskList);


        dbHelper = new DBHelper(this);
        preferenceManager = new PreferenceManager(getApplicationContext());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(dashboard.this, "Logged out", Toast.LENGTH_SHORT).show();
                preferenceManager.clear();
                Intent i = new Intent(dashboard.this,splashscreen.class);
                startActivity(i);
            }
        });

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(dashboard.this,MainActivity.class);
                startActivity(i);
            }
        });

//        getting name from the database
        Intent i = getIntent();
        Email = i.getStringExtra("email");

        Cursor cursor = dbHelper.getData();
        getUserName(cursor,Email);
        welcomeTxt.setText("Welcome "+ Name);

    }

    private void getUserName(Cursor cursor, String email) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(email)) {
                    Name = cursor.getString(1).toString();
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        taskList.setLayoutManager(linearLayoutManager);
        taskList.setHasFixedSize(true);
        taskDBHelper = new TaskDBHelper(this);
        allTasks = taskDBHelper.listTasks();

        if(allTasks.size() > 0){
            taskList.setVisibility(View.VISIBLE);
            mAdapter = new TaskAdapter(this, allTasks);
            taskList.setAdapter(mAdapter);

        }else {
            taskList.setVisibility(View.GONE);
            Toast.makeText(this, "There is no task in the database. Start adding now", Toast.LENGTH_LONG).show();
        }
    }
}