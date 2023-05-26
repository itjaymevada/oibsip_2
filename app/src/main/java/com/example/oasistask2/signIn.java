package com.example.oasistask2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.oasistask2.sql.DBHelper;

public class signIn extends AppCompatActivity {

    ImageView back;
    EditText email,password;
    TextView forgotPassword,signIn,signUp;
    DBHelper dbHelper;
    private PreferenceManager preferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        forgotPassword = findViewById(R.id.forgettPassword);
        signIn = findViewById(R.id.signIn);
        signUp = findViewById(R.id.txt_signUp);
        back = findViewById(R.id.back);
        dbHelper = new DBHelper(this);

        preferenceManager = new PreferenceManager(getApplicationContext());

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(email,password);
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(com.example.oasistask2.signIn.this, com.example.oasistask2.signUp.class);
                startActivity(i);
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(signIn.this, "This feature will be implemented soon!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void login(EditText email,EditText password) {
        String eMail = email.getText().toString();
        String Password = password.getText().toString();
        Cursor cursor = dbHelper.getData();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No user exist in Database", Toast.LENGTH_SHORT).show();
        }
        if (loginCheck(cursor,eMail,Password)){
            Intent i = new Intent(signIn.this,dashboard.class);
            i.putExtra("email",eMail);
            preferenceManager.putString("email",eMail);
            preferenceManager.putBoolean("autoLogin",true);
            startActivity(i);
        }else{
            AlertDialog.Builder builder = new AlertDialog.Builder(signIn.this);
            builder.setCancelable(true);
            builder.setTitle("Wrong Credential");
            builder.setMessage("Email or Password is wrong!!");
            builder.show();
        }
        dbHelper.close();
    }

    public static boolean loginCheck(Cursor cursor,String emailCheck,String passCheck) {
        while (cursor.moveToNext()){
            if (cursor.getString(0).equals(emailCheck)) {
                if (cursor.getString(2).equals(passCheck)) {
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}