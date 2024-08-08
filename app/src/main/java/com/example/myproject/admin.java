package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class admin extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ArrayList<card> card;

    recycle_add_product recycleViewAdapter;

    RecyclerView recyclerView;
    Button addBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        addBtn =findViewById(R.id.addBtn);

        databaseHelper =new DatabaseHelper(this);
        card=databaseHelper.getData();

        recycleViewAdapter = new recycle_add_product(this , card);
        recyclerView= findViewById(R.id.reCyclerView);

        recyclerView.setAdapter(recycleViewAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 2));

        addBtn.setOnClickListener(v->{
            Intent i = new Intent(this , Add_Product.class);
            startActivity(i);
        });
    }
}