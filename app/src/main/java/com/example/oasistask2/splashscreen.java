package com.example.oasistask2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oasistask2.sql.DBHelper;

public class splashscreen extends AppCompatActivity {

    RelativeLayout newUserLyt;
    ProgressBar loading;
    DBHelper dbHelper;

    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        newUserLyt = findViewById(R.id.newUserLyt);
        loading = findViewById(R.id.loading);

        dbHelper = new DBHelper(this);

        preferenceManager = new PreferenceManager(getApplicationContext());


        TextView getStart = findViewById(R.id.getStartedTxt);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkAcc();
            }
        },3000);


        getStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(splashscreen.this,signUp.class);
                startActivity(i);
            }
        });

    }

    private void checkAcc() {
            boolean autoLog = preferenceManager.getBoolean("autoLogin");
            String email = preferenceManager.getString("email");
            if (autoLog){
                Intent i = new Intent(splashscreen.this,dashboard.class);
                i.putExtra("email",email);
                startActivity(i);
            }else{
                Toast.makeText(this, "No users saved in this device", Toast.LENGTH_SHORT).show();
                loading.setVisibility(View.GONE);
                newUserLyt.setVisibility(View.VISIBLE);
            }

        }

}