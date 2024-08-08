package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;

public class ListProduct extends AppCompatActivity {

    RecyclerView product;
    ArrayList<card> list_product;
    ProductListAdapter productAdapter;
    DatabaseHelper dbProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_product);
        dbProduct=new DatabaseHelper(this);
        Intent in=getIntent();
        String category=in.getStringExtra("name");
        list_product=dbProduct.getDataSpecialData(category);
        product=findViewById(R.id.productView);
        productAdapter=new ProductListAdapter(this,list_product);
        product.setAdapter(productAdapter);
        product.setLayoutManager(new GridLayoutManager(this , 2));

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.cart:
                Intent moveCart=new Intent(this,Shopping_cart.class);
                moveCart.putExtra("name","Cart");
                startActivity(moveCart);
                return true;
            case R.id.skin:
                Intent moveSkine=new Intent(this,ListProduct.class);
                moveSkine.putExtra("name","SkinCare");
                startActivity(moveSkine);
                return true;

            case R.id.baby:
                Intent movebaby=new Intent(this,ListProduct.class);
                movebaby.putExtra("name","BabyCare");
                startActivity(movebaby);
                return true;

            case R.id.medicin:
                Intent movedrugs=new Intent(this,ListProduct.class);
                movedrugs.putExtra("name","Drugs&medicine");
                startActivity(movedrugs);
                return true;

            case R.id.hygin:
                Intent movehygin=new Intent(this,ListProduct.class);
                movehygin.putExtra("name","Hygiene");
                startActivity(movehygin);
                return true;

            case R.id.setin:
                Intent moveseting=new Intent(this,Setting.class);
                moveseting.putExtra("name","Setting");
                startActivity(moveseting);
                return true;

            case R.id.log:
                Intent movelogout=new Intent(this,Login.class);
                movelogout.putExtra("name","Hygiene");
                startActivity(movelogout);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.customer_menu,menu);
        return true;
    }
}