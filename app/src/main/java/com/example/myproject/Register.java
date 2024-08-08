package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText name,pass,num;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        databaseHelper=new DatabaseHelper(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
    public void onClickReg(View view){
        name=findViewById(R.id.editTextName);
        num=findViewById(R.id.editTextNumber);
        pass=findViewById(R.id.editTextPassword);
        String username=name.getText().toString();
        String usernum=num.getText().toString();
        String userpass=pass.getText().toString();

        if(username.isEmpty()&&usernum.isEmpty()&&userpass.isEmpty()){

            Toast.makeText(this, "Cannot Submit Empty Fields", Toast.LENGTH_SHORT).show();
        }
        else{
            databaseHelper.addUser(username,usernum,userpass);
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,Categoy_customer.class));
        }

    }
}