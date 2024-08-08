package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    static String usernumber;
    EditText num,pass;
    TextView signup;
    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        databaseHelper=new DatabaseHelper(this);
        signup=findViewById(R.id.signup);
        signup.setOnClickListener(view ->{
            startActivity(new Intent(this,Register.class));
        });

    }
    public void onLogin(View view){
       num=findViewById(R.id.editTextLoginName);
       pass=findViewById(R.id.editTextLoginPass);
       usernumber=num.getText().toString();
       String userpassword=pass.getText().toString();
       boolean isAdmin=usernumber.equals("admin")&&userpassword.equals("admin");
       if(isAdmin){
           startActivity(new Intent(this,admin.class));
       }
        else if (databaseHelper.checkUser(usernumber, userpassword)) {
            Toast.makeText(this, "Login successful"+databaseHelper.customerid, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Categoy_customer.class));
        } else {
            Toast.makeText(this, "Invalid", Toast.LENGTH_SHORT).show();
        }
        }
}