package com.example.myproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Update_Prodoct extends AppCompatActivity {
    String selectedOption;
    recycle_update_product recycleViewAdapter;
    DatabaseHelper dbHelber;
    byte[] mybyteimg;
    Bitmap bitmap;

    ImageView pro_img;
    Uri uriStore;
    EditText pro_name;
    EditText pro_price;
    Button select_img;
    Button update_pro;
    Button back_pro;
    String category;

    RadioButton skin_Radio;
    RadioButton baby_Radio;
    RadioButton hygin_Radio;
    RadioButton drugs_Radio;
    RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_prodoct);
        pro_name=findViewById(R.id.product_name);
        pro_img=findViewById(R.id.product_img);
        pro_price=findViewById(R.id.product_price);
        Intent move=this.getIntent();

        card c1=(card) move.getSerializableExtra("card");
        String name=c1.getName();
        pro_name.setText(name);
        String price=c1.getPrice();
        pro_price.setText(price);
        category=c1.getCategory();

        mybyteimg=c1.getImg();
        bitmap=recycle_add_product.convertCompressedByteArrayToBitmap(mybyteimg);
        pro_img.setImageBitmap(bitmap);
        select_img=findViewById(R.id.selectImg);
        update_pro=findViewById(R.id.update_btn);
        back_pro=findViewById(R.id.back_add_btn);

        radioGroup=findViewById(R.id.radioGroup);
        skin_Radio=findViewById(R.id.skinCare);
        baby_Radio=findViewById(R.id.babyCare);
        drugs_Radio=findViewById(R.id.Drugs);
        hygin_Radio=findViewById(R.id.hygiene);
        if(category.equals(skin_Radio.getText().toString()))
            skin_Radio.setChecked(true);
        else if(category.equals(baby_Radio.getText().toString()))
            baby_Radio.setChecked(true);
        else if(category.equals(drugs_Radio.getText().toString()))
            drugs_Radio.setChecked(true);
        else if(category.equals(hygin_Radio.getText().toString()))
            hygin_Radio.setChecked(true);

        dbHelber = new DatabaseHelper(this);


        select_img.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 100);

        });
        back_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Update_Prodoct.this,admin.class);
                startActivity(intent);
            }
        });

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                category = radioButton.getText().toString();
                Toast.makeText(Update_Prodoct.this, "Selected option: " + category, Toast.LENGTH_SHORT).show();
            }
        });

        String id=move.getStringExtra("id");

        update_pro.setOnClickListener(v -> {

            Toast.makeText(Update_Prodoct.this, "Selected option: " + category+" id "+id, Toast.LENGTH_SHORT).show();
            card crd = new card(
                    pro_name.getText().toString(),
                    pro_price.getText().toString(),
                    recycleViewAdapter.convertBitmapToByteArray(bitmap),
                    category,id);

            dbHelber.Update_product(crd,id);

            startActivity(new Intent(this,admin.class));

        });

    }

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            uriStore = uri;
            pro_img.setImageURI(uri);
            bitmap = ((BitmapDrawable) pro_img.getDrawable()).getBitmap();
            mybyteimg = recycleViewAdapter.convertBitmapToByteArray(bitmap);
        }
    }
}