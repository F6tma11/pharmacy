package com.example.myproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class Categoy_customer extends AppCompatActivity {
    CardView cardSkin;
    CardView cardBaby;
    CardView cardDrugs;
    CardView cardHygin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_categoy_customer);
        setTitle("Settings");
        cardSkin=findViewById(R.id.cardSkin);
        cardSkin.setOnClickListener(v->{
            Intent moveSkine=new Intent(this,ListProduct.class);
            moveSkine.putExtra("name","SkinCare");
            startActivity(moveSkine);
        });

        cardBaby=findViewById(R.id.cardBaby);
        cardBaby.setOnClickListener(v->{
            Intent move=new Intent(this,ListProduct.class);
            move.putExtra("name","BabyCare");
            startActivity(move);
        });

        cardDrugs=findViewById(R.id.cardDrugs);
        cardDrugs.setOnClickListener(v->{
            Intent move=new Intent(this,ListProduct.class);
            move.putExtra("name","Drugs&medicine");
            startActivity(move);
        });

        cardHygin=findViewById(R.id.cardHygin);
        cardHygin.setOnClickListener(v->{
            Intent move=new Intent(this,ListProduct.class);
            move.putExtra("name","Hygiene");
            startActivity(move);
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.cart:
                Intent moveCart=new Intent(this,Shopping_cart.class);
                moveCart.putExtra("name","Cart");
                startActivity(moveCart);
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
        inflater.inflate(R.menu.cataeg_menu,menu);
        return true;
    }
}