package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class
product_page extends AppCompatActivity {

    TextView name_pro;
    TextView price_pro;


    Bitmap b1;
    TextView pices_pro;

    Button add_to_cart;
    Button add_pice,minus_pice;

    DatabaseHelper databaseHelper;
    byte[] mybyteimg;
    Bitmap bitmap1;
    ImageView img;
    DatabaseHelper db;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_page);
        img=findViewById(R.id.product_image);

        Intent move1=this.getIntent();
        card c1=(card) move1.getSerializableExtra("card");

        System.out.println(c1.toString());
        String name=c1.getName();
        String price=c1.getPrice();

        mybyteimg=c1.getImg();
        bitmap1=ProductListAdapter.convertCompressedByteArrayToBitmap(mybyteimg);

        img.setImageBitmap(bitmap1);


        String id=c1.getId();
        name_pro=findViewById(R.id.product_name);
        price_pro=findViewById(R.id.product_price);
        pices_pro=findViewById(R.id.product_pices);

        name_pro.setText(name);
        price_pro.setText(price);
        add_to_cart=findViewById(R.id.gocart);
        add_pice=findViewById(R.id.add);
        minus_pice=findViewById(R.id.mins);
        add_pice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(pices_pro.getText().toString())+1;
                pices_pro.setText(num+"");
            }
        });
        minus_pice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num=Integer.parseInt(pices_pro.getText().toString())-1;
                pices_pro.setText(num+"");
            }
        });
        String pices=pices_pro.getText().toString();
        mybyteimg=ProductListAdapter.convertBitmapToByteArray(bitmap1);
        cart cart1=new cart(name,price,id,mybyteimg,Login.usernumber,pices);
        db=new DatabaseHelper(this);
        add_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.add_order(cart1);
                startActivity(new Intent(product_page.this,Product_Cart.class));

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.customer_menu,menu);
        return true;
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


}