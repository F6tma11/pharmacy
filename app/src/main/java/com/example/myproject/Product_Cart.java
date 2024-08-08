package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Product_Cart extends AppCompatActivity {

    cart car2;
    ArrayList<cart>arrayList;
    DatabaseHelper databaseHelper;
    ListView listView;
    listviewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_cart);
        listView=findViewById(R.id.listview);
        databaseHelper=new DatabaseHelper(this);
        arrayList=databaseHelper.Select_cart(Login.usernumber);

        adapter=new listviewAdapter(Product_Cart.this,R.layout.content_cart,arrayList);
        listView.setAdapter(adapter);

    }
}