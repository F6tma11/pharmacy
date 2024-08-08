package com.example.myproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class ProductListAdapter extends RecyclerView.Adapter<ProductListAdapter.MyViewHolder> {

    private Context context;
    private ArrayList<card> product_list;


    public ProductListAdapter(Context context, ArrayList<card> product_list) {
        this.context = context;
        this.product_list = product_list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.product_card1,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.pro_image.setImageBitmap(convertCompressedByteArrayToBitmap(product_list.get(position).getImg()));
        holder.pro_name.setText(product_list.get(position).getName());
        holder.pro_price.setText(product_list.get(position).getPrice());

        holder.cardView1.setOnClickListener(v->{
            Intent intent =new Intent(context ,product_page.class);
            intent.putExtra("card",product_list.get(position));

            context.startActivity(intent);

        });

    }

    @Override
    public int getItemCount() {
        return product_list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView pro_image;
        CardView cardView1;
        TextView pro_name;
        TextView pro_price;
        TextView pro_cat;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView1 = itemView.findViewById(R.id.cardView);
            pro_image = itemView.findViewById(R.id.imageView);
            pro_name = itemView.findViewById(R.id.textViewName);
            pro_price = itemView.findViewById(R.id.textViewPrice);
            pro_cat = itemView.findViewById(R.id.textViewCategory);
        }
    }

    public static byte[] convertBitmapToByteArray(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if (bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)){
            return stream.toByteArray();

        } else if (bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)) {
            return stream.toByteArray();

        }else if (bitmap.compress(Bitmap.CompressFormat.WEBP, 100, stream))
            return stream.toByteArray();

        return stream.toByteArray();

    }

    public static Bitmap convertCompressedByteArrayToBitmap(byte[] src){
        ByteArrayInputStream stream = new ByteArrayInputStream(src);
        Bitmap bitmap = BitmapFactory.decodeStream(stream);
        return bitmap;
    }


}
