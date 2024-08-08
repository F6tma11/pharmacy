package com.example.myproject;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Bitmap;
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

public class Add_Product extends AppCompatActivity {

    byte[] mybyteimg;
    Bitmap bitmap;
    ImageView myImageView;
    Uri uriStore;
    EditText editTextName, editTextPrice;
    Button saveBtn, selectImg;
    DatabaseHelper dbHelber;
    RadioGroup radioGroup;
    String selectedOption;

    recycle_add_product recycleViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        dbHelber = new DatabaseHelper(this);

        radioGroup = findViewById(R.id.radioGroup);
        myImageView = findViewById(R.id.myImageView);
        editTextName = findViewById(R.id.editTextName);
        editTextPrice = findViewById(R.id.editTextPrice);

        saveBtn = findViewById(R.id.saveBtn);
        selectImg = findViewById(R.id.selectImg);

        selectImg.setOnClickListener(v -> {
            Intent photoPickerIntent = new Intent(Intent.ACTION_GET_CONTENT);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, 100);

        });


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(checkedId);
                selectedOption = radioButton.getText().toString();
                Toast.makeText(Add_Product.this, "Selected option: " + selectedOption, Toast.LENGTH_SHORT).show();
            }
        });



        saveBtn.setOnClickListener(v -> {

            card crd = new card(
                    editTextName.getText().toString(),
                    editTextPrice.getText().toString(),
                    recycleViewAdapter.convertBitmapToByteArray(bitmap),
                    selectedOption,dbHelber.id);

            dbHelber.insert_product(crd);

            startActivity(new Intent(this,admin.class));
        });


    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            Uri uri = data.getData();
            uriStore = uri;
            myImageView.setImageURI(uri);
            bitmap = ((BitmapDrawable) myImageView.getDrawable()).getBitmap();
            mybyteimg = recycleViewAdapter.convertBitmapToByteArray(bitmap);
        }
    }
}