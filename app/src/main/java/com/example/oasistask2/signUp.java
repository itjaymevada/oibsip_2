package com.example.oasistask2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oasistask2.sql.DBHelper;

public class signUp extends AppCompatActivity {

    ImageView back;
    EditText name,email,password,confirm_password;
    TextView signUp,signIn;
    DBHelper dbHelper;
    private PreferenceManager preferenceManager;

    String userName,userEmail,userPassword,userConfirm_Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password =  findViewById(R.id.password);
        confirm_password = findViewById(R.id.confirmPassword);
        signUp = findViewById(R.id.signUp);
        signIn = findViewById(R.id.txt_signIn);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccount();

            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(signUp.this, signIn.class);
                startActivity(i);
            }
        });

    }

    private void createAccount() {
        userName = name.getText().toString().trim();
        userEmail = email.getText().toString().trim();
        userPassword = password.getText().toString().trim();
        userConfirm_Password = confirm_password.getText().toString().trim();

        if (userName.isEmpty()||userEmail.isEmpty()||userPassword.isEmpty()||userConfirm_Password.isEmpty() ){
            Toast.makeText(signUp.this, "Please enter all details", Toast.LENGTH_SHORT).show();
        }
        else{
            if(userPassword.equals(userConfirm_Password)){
                dbHelper = new DBHelper(getApplicationContext());
                boolean b = dbHelper.insetUserData(userName,userEmail,userPassword);
                if (b){
                    Toast.makeText(signUp.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(signUp.this,dashboard.class);
                    preferenceManager = new PreferenceManager(getApplicationContext());
                    preferenceManager.putBoolean("autoLogin",true);
                    preferenceManager.putString("email",userEmail);
                    preferenceManager.putString("name",userName);
                    startActivity(i);
                }
            }else{
                Toast.makeText(signUp.this, "Password must be same", Toast.LENGTH_SHORT).show();
            }
        }
    }


}